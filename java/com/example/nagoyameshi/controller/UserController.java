package com.example.nagoyameshi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.SalesService;
import com.example.nagoyameshi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserRepository userRepository;
	private final UserService userService;
	private final SalesService salesService;
	
	public UserController(UserRepository userRepository, UserService userService,SalesService salesService) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.salesService = salesService;
	}
	
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		
		model.addAttribute("user", user);
		
		return "user/index";
	}
	
	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,  Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getKana(), user.getPostCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail());
		
		model.addAttribute("userEditForm", userEditForm);
		
		return "user/edit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute
			@Validated UserEditForm userEditForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		// メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
		if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
			bindingResult.addError(fieldError);
		}
		
		if (bindingResult.hasErrors()) {
			return "user/edit";	
		}
		
		userService.update(userEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
		
		return "redirect:/user";
	}
}
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//    private final UserRepository userRepository;
//    private final UserService userService;
//    private final UserEditForm userEditForm;
//
//    public UserController(UserRepository userRepository, UserService userService, UserEditForm userEditForm) {
//        this.userRepository = userRepository;
//        this.userService = userService;
//        this.userEditForm = userEditForm;
//    }
//
//    @GetMapping("/edit")
//    public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
//        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
//        this.userEditForm.setId(user.getId());
//        this.userEditForm.setName(user.getName());
//        this.userEditForm.setKana(user.getKana());
//        this.userEditForm.setPostCode(user.getPostCode());
//        this.userEditForm.setAddress(user.getAddress());
//        this.userEditForm.setPhoneNumber(user.getPhoneNumber());
//        this.userEditForm.setEmail(user.getEmail());
//
//        model.addAttribute("userEditForm", this.userEditForm);
//        return "user/edit";
//    }
//
//    @PostMapping("/update")
//    public String update(@Validated @ModelAttribute("userEditForm") UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        // メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
//        if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
//            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
//            bindingResult.addError(fieldError);
//        }
//
//        // パスワードとパスワード（確認用）の入力値が一致しなければ、BindingResultオブジェクトにエラー内容を追加
//        if (!userService.isSamePassword(userEditForm.getPassword(), userEditForm.getPasswordConfirmation())) {
//            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
//            bindingResult.addError(fieldError);
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "user/edit";
//        }
//        userService.update(userEditForm);
//        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
//
//        return "redirect:/user";
//    }
//}