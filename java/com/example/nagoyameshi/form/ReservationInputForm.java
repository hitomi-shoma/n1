package com.example.nagoyameshi.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "予約日時を選択してください。")
	private String reservationDateTime;
	
	@NotNull(message = "予約人数を入力してください。")
	@Min(value = 1, message = "1人以上に設定してください。")
	private Integer numberOfPeople;

	public LocalDateTime getReservationDateTime() {
	    if (reservationDateTime == null || reservationDateTime.isEmpty()) {
	        return null;
	    }
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	    return LocalDateTime.parse(reservationDateTime, formatter);
	}
}

//訂正１　予約日時を取得する
//public LocalDateTime getReservationDateTime() {
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//	return LocalDateTime.parse(this.reservationDateTime, formatter);