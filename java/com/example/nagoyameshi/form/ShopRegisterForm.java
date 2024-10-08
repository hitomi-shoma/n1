package com.example.nagoyameshi.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShopRegisterForm {
	@NotBlank(message = "店名を入力してください。")
	private String name;
	
	private MultipartFile imageFile;
	
	@NotNull(message = "カテゴリーを選択してください。")
//	private Category category;
	private Integer category;
	
	@NotBlank(message = "店の説明を入力してください。")
	private String description;
	
	@NotNull(message = "最安値を入力してください。")
	@Min(value = 1, message = "1円以上に設定してください。")
	private Integer lowerPrice;
	
	@NotNull(message = "最高値を入力してください。")
	@Min(value = 1, message = "1円以上に設定してください。")
	private Integer upperPrice;
	
	@NotNull(message = "定員を入力してください。")
	@Min(value = 1, message = "1人以上に設定してください。")
	private Integer capacity;
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postCode;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;
}