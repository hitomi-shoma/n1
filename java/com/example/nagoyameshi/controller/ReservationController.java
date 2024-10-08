package com.example.nagoyameshi.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.ShopRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.ReservationService;
import com.example.nagoyameshi.service.StripeReservationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final ShopRepository shopRepository;
    private final ReservationService reservationService;
    private final StripeReservationService stripeReservationService;
    
    public ReservationController(ReservationRepository reservationRepository, ShopRepository shopRepository, ReservationService reservationService,StripeReservationService stripeReservationService) {
        this.reservationRepository = reservationRepository;
        this.shopRepository = shopRepository;
        this.reservationService = reservationService;
        this.stripeReservationService = stripeReservationService;
    }

    @GetMapping("/reservations")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
        User user = userDetailsImpl.getUser();
        Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
        
        model.addAttribute("reservationPage", reservationPage);
        
        return "reservations/index";
    }
    
    @GetMapping("/shops/{id}/reservations/input")//予約フォームの送信先にReservationInputFormを渡す
    public String input(@PathVariable(name = "id") Integer id,
    		@ModelAttribute
    		@Validated ReservationInputForm reservationInputForm,
    		BindingResult bindingResult,
    		RedirectAttributes redirectAttributes,
    		Model model)
    {
    	Shop shop = shopRepository.getReferenceById(id);
    	Integer numberOfPeople = reservationInputForm.getNumberOfPeople();
    	Integer capacity = shop.getCapacity();
    	
    	if (numberOfPeople != null) {
    		if (!reservationService.isWithinCapacity(numberOfPeople, capacity)) {
    			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "numberOfPeople", "予約人数が定員を超えています。");
    			bindingResult.addError(fieldError);
    		}
    	}
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("shop", shop);
    		model.addAttribute("errorMessage", "予約内容に不備があります。");
    		
    		return "shops/show";
    	}
    		
    	redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);
    	return "redirect:/shops/{id}/reservations/confirm";
    }
    
    @GetMapping("/shops/{id}/reservations/confirm")
    public String confirm(@PathVariable(name = "id") Integer id,
                          @ModelAttribute ReservationInputForm reservationInputForm,
                          @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                          HttpServletRequest httpServletRequest,
                          Model model) {
        Shop shop = shopRepository.getReferenceById(id);
        User user = userDetailsImpl.getUser();

        // 予約日時を取得する
        LocalDateTime reservationDateTime = reservationInputForm.getReservationDateTime();

        // 金額を計算する
        Integer price = shop.getLowerPrice();
        Integer amount = reservationService.calculateAmount(reservationInputForm.getNumberOfPeople(), price);

        ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(
            shop.getId(),
            user.getId(),
            reservationDateTime,
            reservationInputForm.getNumberOfPeople(),
            amount
        );

        String sessionId = stripeReservationService.createStripeSession(shop.getName(), reservationRegisterForm, httpServletRequest);

        model.addAttribute("shop", shop);
        model.addAttribute("reservationRegisterForm", reservationRegisterForm);
        model.addAttribute("sessionId", sessionId);
        return "reservations/confirm";
    }
}