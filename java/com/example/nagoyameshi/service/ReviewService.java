package com.example.nagoyameshi.service;

//import org.springframework.stereotype.Service;
//
//import com.example.nagoyameshi.entity.Review;
//import com.example.nagoyameshi.repository.ReviewRepository;
//
//@Service
//public class ReviewService {
//
//    private final ReviewRepository reviewRepository;
//
//    public ReviewService(ReviewRepository reviewRepository) {
//        this.reviewRepository = reviewRepository;
//    }
//    //指定されたreviewIdに対応するReviewを取得する。Reviewが見つからない場合はnullを返す。
//    public Review getReviewById(Integer reviewId) {
//        return reviewRepository.findById(reviewId).orElse(null);
//    }
//    //Reviewをデータベースに保存
//    public void saveReview(Review review) {
//        reviewRepository.save(review);
//    }
//    //指定されたreviewIdに対応するReviewを削除する
//    public void deleteReviewById(Integer reviewId) {
//        reviewRepository.deleteById(reviewId);
//    }

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewRegisterForm;
import com.example.nagoyameshi.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;        
    
    public ReviewService(ReviewRepository reviewRepository) {        
        this.reviewRepository = reviewRepository;        
    }     
    
    @Transactional
    public void create(Shop shop, User user, ReviewRegisterForm reviewRegisterForm) {
        Review review = new Review();        
        
        review.setShop(shop);                
        review.setUser(user);
        review.setScore(reviewRegisterForm.getScore());
        review.setReviewText(reviewRegisterForm.getReviewText());
                    
        reviewRepository.save(review);
    }     
    
    @Transactional
    public void update(ReviewEditForm reviewEditForm) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());        
        
        review.setScore(reviewEditForm.getScore());                
        review.setReviewText(reviewEditForm.getReviewText());
                    
        reviewRepository.save(review);
    }    
    
    public boolean hasUserAlreadyReviewed(Shop shop, User user) {
        return reviewRepository.findByShopAndUser(shop, user) != null;
    }
}