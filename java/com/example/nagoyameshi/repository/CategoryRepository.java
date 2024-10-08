package com.example.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
	//カテゴリー検索	
	public Category findByCategoryName(String categoryName);
	
	// カテゴリーIDから取得
	public Category getReferenceById(Integer categoryId);
}