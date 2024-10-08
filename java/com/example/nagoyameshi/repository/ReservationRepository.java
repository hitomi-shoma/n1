package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation,Integer>{
	//ユーザーの予約を登録した降順で表示
	public Page<Reservation> findByUserOrderByCreatedAtDesc(User user,Pageable pageable);
	// ユーザーに関連する予約を取得（ページング対応）
	public Page<Reservation> findByUserOrderByReservationDateDesc(User user, Pageable pageable);
}
