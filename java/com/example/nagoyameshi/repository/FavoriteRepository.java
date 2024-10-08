package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	//指定されたUserと紐づいているお気に入りを、作成日時の降順でページングして取得する
	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
//	//指定されたUserと指定されたShopの組み合わせに対応するお気に入りを取得する
//	List<Favorite> getByUserAndShop(User user, Shop shop);
////	public Favorite getByUserAndShop(User user, Shop shop);
//	//指定されたUserと指定されたShopの組み合わせに対応するお気に入りを削除する
//	@Transactional
//	public void deleteByUserAndShop(User user, Shop shop);
	public Favorite findByShopAndUser(Shop shop, User user);
}