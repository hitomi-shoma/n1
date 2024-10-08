package com.example.nagoyameshi.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.ShopRepository;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final ShopRepository shopRepository;
	private final UserRepository userRepository;
	
	public ReservationService(ReservationRepository reservationRepository, ShopRepository shopRepository, UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.shopRepository = shopRepository;
		this.userRepository = userRepository;
	}
	
	
//	@Transactional
//    public void create(ReservationRegisterForm reservationRegisterForm) {
//        Reservation reservation = new Reservation();
//        Shop shop = shopRepository.getReferenceById(reservationRegisterForm.getShopId());
//        User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
//        LocalDateTime reservationDateTime = reservationRegisterForm.getReservationDateTime();
//        reservation.setShop(shop);
//        reservation.setUser(user);
//        reservation.setReservationDate(reservationDateTime);
//        reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
//        reservation.setAmount(reservationRegisterForm.getAmount());
//        reservationRepository.save(reservation);
//    }
//
//    public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
//        return numberOfPeople <= capacity;
//    }
//
//    public Integer calculateAmount(Integer numberOfPeople, Integer lowerPrice) {
//        return lowerPrice * numberOfPeople;
//    }
//
//    public ReservationRegisterForm createReservationRegisterForm(Integer shopId, Integer userId, LocalDateTime reservationDateTime, Integer numberOfPeople, Integer amount) {
//        return new ReservationRegisterForm(shopId, userId, reservationDateTime, numberOfPeople, amount);
//    }
    
//訂正１	public void create(ReservationRegisterForm reservationRegisterForm) {
//		Reservation reservation = new Reservation();
//		Shop shop = shopRepository.getReferenceById(reservationRegisterForm.getShopId());
//		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
//		LocalDateTime reservationDateTime = reservationRegisterForm.getReservationDateTime();

	public void create(Map<String, String> paymentIntentObject) {
		Reservation reservation = new Reservation();
		
		Integer shopId = Integer.valueOf(paymentIntentObject.get("shopId"));	
		Integer userId = Integer.valueOf(paymentIntentObject.get("userId"));	
		Shop shop = shopRepository.getReferenceById(shopId);
		User user = userRepository.getReferenceById(userId);
		LocalDateTime reservationDateTime = LocalDateTime.parse(paymentIntentObject.get("reservationDateTime"));
		Integer numberOfPeople = Integer.valueOf(paymentIntentObject.get("numberOfPeople"));	
		Integer amount = Integer.valueOf(paymentIntentObject.get("amount"));
		
		reservation.setShop(shop);
		reservation.setUser(user);
		reservation.setReservationDate(reservationDateTime); // 時間情報を保存
		reservation.setNumberOfPeople(numberOfPeople);
		reservation.setAmount(amount);
		
		reservationRepository.save(reservation);
	}
	
	// 予約人数が定員以下かどうかをチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
		return numberOfPeople <= capacity;
	}
	
	// 料金を計算する
	public Integer calculateAmount(Integer numberOfPeople, Integer lowerPrice) {		
		int amount = lowerPrice * numberOfPeople;	
		return amount;	
	}
}