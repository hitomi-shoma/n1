package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop,Integer>{
	//名前の部分一致/ページングして取得する
	public Page<Shop> findByNameLike(String keyword,Pageable pageable);
	//名前or住所の部分一致/登録日の新しい順に並べ替えて表示
	public Page<Shop> findByNameLikeOrAddressLikeOrderByCreatedAtDesc(String nameKeyword,String addressKeyword,Pageable pageable);
	//名前or住所の部分一致/最低価格の昇順で並べる。
	public Page<Shop> findByNameLikeOrAddressLikeOrderByLowerPriceAsc(String nameKeyword,String addressKeyword,Pageable pageable);
	//住所の部分一致/最低価格の昇順で並べる。
	public Page<Shop> findByAddressLikeOrderByLowerPriceAsc(String area, Pageable pageable);
	//住所の部分一致/登録日の新しい順に並べ替えて表示
	public Page<Shop> findByAddressLikeOrderByCreatedAtDesc(String area,Pageable pageable);
	//指定した金額以下/登録日の新しい順に並べ替えて表示
	public Page<Shop> findByLowerPriceLessThanEqualOrderByCreatedAtDesc(Integer lowerPrice,Pageable pageable);
	//登録日の新しい順に並べる。
	public Page<Shop> findAllByOrderByCreatedAtDesc(Pageable pageable);
	//最低価格を昇順に並べる。
	public Page<Shop> findAllByOrderByLowerPriceAsc(Pageable pageable);
	
//	Page<Shop> findByCategoriesNameContainingOrNameLikeOrAddressLikeOrderByCreatedAtDesc(String categoryName, String nameKeyword, String addressKeyword, Pageable pageable);
//	
//	List<Shop> findTop10ByCategoriesNameContainingOrderByCreatedAtDesc(String categoryName);
	
//	カテゴリー検索
	public Page<Shop> findByCategoryOrderByCreatedAtDesc(Category category,Pageable pageable);
	
	//登録日の新しい順に、上位10件をリスト表示する。
	public List<Shop> findTop10ByOrderByCreatedAtDesc();
}