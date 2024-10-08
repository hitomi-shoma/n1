package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Sale;

public interface SalesRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByUserId(Integer userId);
}