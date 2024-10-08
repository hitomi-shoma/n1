package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.repository.ShopRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.FavoriteService;
import com.example.nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/shops")
public class ShopController {
    private final ShopRepository shopRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final FavoriteRepository favoriteRepository;
    private final FavoriteService favoriteService;
    private final CategoryRepository categoryRepository;

    public ShopController(ShopRepository shopRepository, ReviewRepository reviewRepository, ReviewService reviewService,
                          FavoriteRepository favoriteRepository, FavoriteService favoriteService,
                          CategoryRepository categoryRepository) {
        this.shopRepository = shopRepository;
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
        this.favoriteRepository = favoriteRepository;
        this.favoriteService = favoriteService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Shop shop = shopRepository.getReferenceById(id);
        Favorite favorite = null;
        boolean hasUserAlreadyReviewed = false;
        boolean isFavorite = false;

        List<Review> newReviews = reviewRepository.findTop6ByShopOrderByCreatedAtDesc(shop);
        long totalReviewCount = reviewRepository.countByShop(shop);

        if (userDetailsImpl != null) {
            User user = userDetailsImpl.getUser();
            hasUserAlreadyReviewed = reviewService.hasUserAlreadyReviewed(shop, user);
            isFavorite = favoriteService.isFavorite(shop, user);
            if (isFavorite) {
                favorite = favoriteRepository.findByShopAndUser(shop, user);
            }
        }

        // 無料会員の場合は、hasUserAlreadyReviewedを常にfalseにする
        if (userDetailsImpl != null && !reviewService.hasUserAlreadyReviewed(shop, userDetailsImpl.getUser())) {
            hasUserAlreadyReviewed = false;
        }

        model.addAttribute("shop", shop);
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        model.addAttribute("favorite", favorite);
        model.addAttribute("hasUserAlreadyReviewed", hasUserAlreadyReviewed);
        model.addAttribute("newReviews", newReviews);
        model.addAttribute("totalReviewCount", totalReviewCount);
        model.addAttribute("isFavorite", isFavorite);

        return "shops/show";
    }

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
                        @RequestParam(name = "area", required = false) String area,
                        @RequestParam(name = "lowerPrice", required = false) Integer lowerPrice,
                        @RequestParam(name = "category", required = false) Category category,
                        @RequestParam(name = "order", required = false, defaultValue = "createdAtDesc") String order,
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model) {

        Page<Shop> shopPage;

        if (keyword != null && !keyword.isEmpty()) {
            shopPage = shopRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
        } else if (area != null && !area.isEmpty()) {
            shopPage = shopRepository.findByAddressLikeOrderByLowerPriceAsc("%" + area + "%", pageable);
        } else if (lowerPrice != null) {
            shopPage = shopRepository.findByLowerPriceLessThanEqualOrderByCreatedAtDesc(lowerPrice, pageable);
        } else if (order != null && order.equals("lowerPriceAsc")) {
            shopPage = shopRepository.findAllByOrderByLowerPriceAsc(pageable);
        } else {
            shopPage = shopRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        model.addAttribute("shopPage", shopPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("area", area);
        model.addAttribute("lowerPrice", lowerPrice);
        model.addAttribute("category", category);
        model.addAttribute("order", order);

        return "shops/index";
    }

    @GetMapping("/category") //	カテゴリー検索
    public String searchByCategory(@RequestParam("category_id") Integer categoryId,
                                   @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
                                   Model model) {

        Page<Shop> shopPage = shopRepository.findByCategoryOrderByCreatedAtDesc(categoryRepository.getReferenceById(categoryId), pageable);

        model.addAttribute("shopPage", shopPage);
        model.addAttribute("category", categoryRepository.getReferenceById(categoryId));
        return "shops/index";
    }
}



//修正２@Controller
//@RequestMapping("/shops")
//public class ShopController {
//	private final ShopRepository shopRepository;
//	private final ReviewRepository reviewRepository;
//	private final ReviewService reviewService;
//	private final FavoriteRepository favoriteRepository;
//	private final FavoriteService favoriteService;
//	private final CategoryRepository categoryRepository;
//	
//	public ShopController(ShopRepository shopRepository, ReviewRepository reviewRepository, ReviewService reviewService,FavoriteRepository favoriteRepository,FavoriteService favoriteService,CategoryRepository categoryRepository) {
//		this.shopRepository = shopRepository;
//		this.reviewRepository = reviewRepository;
//		this.reviewService = reviewService;
//		this.favoriteRepository = favoriteRepository;
//		this.favoriteService = favoriteService;
//		this.categoryRepository = categoryRepository;
//	}
//	
//	@GetMapping
//	public String index(@RequestParam(name = "keyword", required = false) String keyword,
//						@RequestParam(name = "area", required = false) String area,
//						@RequestParam(name = "lowerPrice",required = false) Integer lowerPrice,
//						@RequestParam(name = "category",required = false) Category category,
//						@RequestParam(name = "order", required = false, defaultValue = "createdAtDesc") String order,
//						@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
//						Model model) {
//		
//		Page<Shop> shopPage;
//		
//		if (keyword != null && !keyword.isEmpty()) {
//			shopPage = shopRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%","%" + keyword + "%",pageable);
//		} else if (area != null && !area.isEmpty()) {
//			shopPage = shopRepository.findByAddressLikeOrderByLowerPriceAsc("%" + area + "%", pageable);
//		}  else if (lowerPrice != null) {
//			shopPage = shopRepository.findByLowerPriceLessThanEqualOrderByCreatedAtDesc(lowerPrice, pageable);
//		} else if (order != null && order.equals("lowerPriceAsc")) {
//			shopPage = shopRepository.findAllByOrderByLowerPriceAsc(pageable);
//		}  else {
//			shopPage = shopRepository.findAllByOrderByCreatedAtDesc(pageable);
//		}
//		
//		model.addAttribute("shopPage", shopPage);
//		model.addAttribute("keyword", keyword);
//		model.addAttribute("area", area);
//		model.addAttribute("lowerPrice", lowerPrice);
//		model.addAttribute("category", category);
//		model.addAttribute("order", order);
//		
//		return "shops/index";
//	}
//	
//	@GetMapping("/{id}")
//    public String show(@PathVariable(name = "id") Integer id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        Shop shop = shopRepository.getReferenceById(id);
//         Favorite favorite = null;
//        boolean hasUserAlreadyReviewed = false;
//         boolean isFavorite = false;
//        
//        if (userDetailsImpl != null) {
//            User user = userDetailsImpl.getUser();
//            hasUserAlreadyReviewed = reviewService.hasUserAlreadyReviewed(shop, user);
//             isFavorite = favoriteService.isFavorite(shop, user);
//             if (isFavorite) {
//                 favorite = favoriteRepository.findByShopAndUser(shop, user);
//             }            
//        }
//        
//        List<Review> newReviews = reviewRepository.findTop6ByShopOrderByCreatedAtDesc(shop);        
//        long totalReviewCount = reviewRepository.countByShop(shop);         
//        
//        model.addAttribute("shop", shop);  
//        model.addAttribute("reservationInputForm", new ReservationInputForm());
//        model.addAttribute("favorite", favorite);
//        model.addAttribute("hasUserAlreadyReviewed", hasUserAlreadyReviewed);
//        model.addAttribute("newReviews", newReviews);        
//        model.addAttribute("totalReviewCount", totalReviewCount);
//        model.addAttribute("isFavorite", isFavorite);        
//        
//        return "shops/show";
//    }
//
//	@GetMapping("/category") //	カテゴリー検索
//	public String searchByCategory(@RequestParam("category_id") Integer categoryId,
//            @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
//            Model model) {
//		
//		Page<Shop> shopPage = shopRepository.findByCategoryOrderByCreatedAtDesc(categoryRepository.getReferenceById(categoryId), pageable);
//		
//		model.addAttribute("shopPage", shopPage);
//		model.addAttribute("category", categoryRepository.getReferenceById(categoryId));
//		return "shops/index";
//	}

	
	
//	//レビュー・お気に入り 修正１↓
//	@GetMapping("/{id}")
//    public String show(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//    		@PathVariable(name = "id") Integer id, Model model) {
//        
//    	Shop shop = shopRepository.getReferenceById(id);
//    	
//        List<Review> reviewList = reviewRepository.findTop6ByShopOrderByUpdatedAtDesc(shop);        
//        Integer userId = null;
//        boolean isPosted = false;
//        boolean isFavorite = false;
//        
//        if (userDetailsImpl != null) {
//        	userId = userDetailsImpl.getUser().getId();
//            
//        	Review userReview = reviewRepository.getByUserAndShop(userDetailsImpl.getUser(), shop);
//        	isPosted = userReview != null;
//        	
//        	List<Favorite> favorite = favoriteRepository.getByUserAndShop(userDetailsImpl.getUser(), shop);	
//        	isFavorite = favorite != null;
//   	 	}
//        
//        model.addAttribute("shop", shop);
//        model.addAttribute("userId", userId);
//        model.addAttribute("isPosted", isPosted);
//        model.addAttribute("reviewList", reviewList);
//        model.addAttribute("reservationInputForm", new ReservationInputForm());
//        model.addAttribute("isFavorite", isFavorite);
//        
//        return "shops/show";     
//    }   
//    
//    //  お気に入りの追加
//    @GetMapping("/{id}/addFavorite")
//    public String addFavorite(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, 
//    						  @PathVariable(name = "id") Integer id,
//    						  RedirectAttributes redirectAttributes,	
//    						  Model model) {
//    	
//    	Shop shop = shopRepository.getReferenceById(id);
//    	Favorite favorite = new Favorite();	
//        favorite.setUser(userDetailsImpl.getUser());
//        favorite.setShop(shop);
//        favoriteRepository.save(favorite);
//        
//        redirectAttributes.addFlashAttribute("message", "お気に入りに追加しました。");
//        
//    	return "redirect:/shops/{id}";
//    }
//    
//    @GetMapping("/{id}/deleteFavorite")
//    public String deleteFavorite(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
//    							 @PathVariable(name = "id") Integer id,	
//    							 RedirectAttributes redirectAttributes,	
//    							 Model model) {
//    	
//    	Shop shop = shopRepository.getReferenceById(id);
//    	favoriteRepository.deleteByUserAndShop(userDetailsImpl.getUser(), shop);	
//    	redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました。");
//    	
//    	return "redirect:/shops/{id}";
//		
//	    }
//	}			