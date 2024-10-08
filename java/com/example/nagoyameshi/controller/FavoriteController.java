package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.ShopRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.FavoriteService;

//@Controller
//@RequestMapping("/favorite")
//public class FavoriteController {
//
//	private final FavoriteRepository favoriteRepository;
//	private final FavoriteService favoriteService;
//	private final ShopRepository shopRepository;
//
//	public FavoriteController (FavoriteRepository favoriteRepository, ShopRepository shopRepository,FavoriteService favoriteService) {
//		this.favoriteRepository = favoriteRepository;
//		this.favoriteService = favoriteService;
//		this.shopRepository = shopRepository;
//	}
//	
//	@GetMapping
//	public String index (@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
//			Model model) {
//		
////		Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByCreatedAt(userDetailsImpl.getUser(), pageable);
//		Page<Favorite> favoritePage = favoriteService.getFavoritesByUser(userDetailsImpl, pageable);
//		
//		model.addAttribute("favoritePage", favoritePage);
//		return "favorite/index";
//	}
//	
//	@GetMapping("/{id}/addFavorite")
//	public String addFavorite(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//	                          @PathVariable(name = "id") Integer id,
//	                          RedirectAttributes redirectAttributes) {
//	    
//	    Shop shop = shopRepository.getReferenceById(id);
//	    Favorite favorite = new Favorite();
//	    favorite.setUser(userDetailsImpl.getUser());
//	    favorite.setShop(shop);
//	    favoriteRepository.save(favorite);
//	    
//	    redirectAttributes.addFlashAttribute("message", "お気に入りに追加しました。");
//	    
//	    return "redirect:/shops/{id}";
//	}
//
//	
//	
////	@GetMapping("/delete")
////	public String delete (@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
////			@RequestParam(name = "shop", required = true) Integer shopId,
////			RedirectAttributes redirectAttributes,
////			Model model) {
////		
////		Shop shop = shopRepository.getReferenceById(shopId);
////		favoriteRepository.deleteByUserAndShop(userDetailsImpl.getUser(), shop);
////		
////		redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました。");
////		
////		return "redirect:/favorite";
////	}
//	
//	@GetMapping("/delete")
//    public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//                         @RequestParam(name = "shop", required = true) Integer shopId,
//                         RedirectAttributes redirectAttributes) {
//        favoriteService.deleteFavoriteByUserAndShop(userDetailsImpl, shopId);
//        redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました。");
//        return "redirect:/favorite";
//

@Controller
public class FavoriteController {
    private final FavoriteRepository favoriteRepository;
    private final ShopRepository shopRepository; 
    private final FavoriteService favoriteService; 
    
    public FavoriteController(FavoriteRepository favoriteRepository, ShopRepository shopRepository, FavoriteService favoriteService) {        
        this.favoriteRepository = favoriteRepository;
        this.shopRepository = shopRepository;
        this.favoriteService = favoriteService;
    }   
    
    @GetMapping("/favorites")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, Model model) {  
        User user = userDetailsImpl.getUser();   
        Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByCreatedAtDesc(user, pageable);       
                            
        model.addAttribute("favoritePage", favoritePage);                
        
        return "favorites/index";
    }    
    
    @PostMapping("/shops/{shopId}/favorites/create")
    public String create(@PathVariable(name = "shopId") Integer shopId,
                         @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,                                                  
                         RedirectAttributes redirectAttributes,
                         Model model)
    {    
        Shop shop = shopRepository.getReferenceById(shopId);
        User user = userDetailsImpl.getUser();        
        
        favoriteService.create(shop, user);
        redirectAttributes.addFlashAttribute("successMessage", "お気に入りに追加しました。");    
        
        return "redirect:/shops/{shopId}";
    }
    
    @PostMapping("/shops/{shopId}/favorites/{favoriteId}/delete")
    public String delete(@PathVariable(name = "favoriteId") Integer favoriteId, RedirectAttributes redirectAttributes) {        
        favoriteRepository.deleteById(favoriteId);
                
        redirectAttributes.addFlashAttribute("successMessage", "お気に入りを解除しました。");
        
        return "redirect:/shops/{shopId}";
    }    
}





//@Controller
//@RequestMapping("/favorite")
//public class FavoriteController {
//
//	private final FavoriteRepository favoriteRepository;
//	private final ShopRepository shopRepository;
//
//	public FavoriteController (FavoriteRepository favoriteRepository, ShopRepository shopRepository) {
//		this.favoriteRepository = favoriteRepository;
//		this.shopRepository = shopRepository;
//	}
//
//	@GetMapping
//	public String index (@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
//			Model model) {
//
//		Page<Favorite> favorite = favoriteRepository.findByUserOrderByCreatedAt(userDetailsImpl.getUser(), pageable);
//
//		model.addAttribute("favoritePage", favorite);
//		return "/favorite/index";
//	}
//
//	@GetMapping("/delete")
//	public String delete (@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//			@RequestParam(name = "shop", required = true) Integer shopId,
//			RedirectAttributes redirectAttributes,
//			Model model) {
//
//		Shop shop = shopRepository.getReferenceById(shopId);
//		favoriteRepository.deleteByUserAndShop(userDetailsImpl.getUser(), shop);
//
//		redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました。");
//
//		return "redirect:/favorite";
//	}
