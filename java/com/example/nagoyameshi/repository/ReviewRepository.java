package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//	//指定されたShopの全てのReviewを、更新日時の降順でページングで取得する
//	public Page<Review> findByShopOrderByUpdatedAtDesc(Shop shop, Pageable pageable);
//	//指定されたShopの最新の6件のReviewエンティティを、更新日時の降順で取得する
//	public List<Review> findTop6ByShopOrderByUpdatedAtDesc(Shop shop);
//	//指定されたUserと指定されたShopの組み合わせに対応するReviewを取得する
////	public List<Review> findByUserAndShop(User use, Shop shop);
//	public Review getByUserAndShop(User use, Shop shop);
	
	public List<Review> findTop6ByShopOrderByCreatedAtDesc(Shop shop);
	List<Review> findByShopAndUser(Shop shop, User user);
    public long countByShop(Shop shop);
    public Page<Review> findByShopOrderByCreatedAtDesc(Shop shop, Pageable pageable);
}