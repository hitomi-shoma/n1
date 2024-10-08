package com.example.nagoyameshi.form;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	
	private Integer shopId;
	
	private Integer userId;
	
	private LocalDateTime reservationDateTime;
	
	private Integer numberOfPeople;
	
	private Integer amount;
}