package com.example.nagoyameshi.form;



import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(message = "氏名を入力してください。")
	private String name;
	
	@NotBlank(message = "フリガナを入力してください。")
	private String kana;
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postCode;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;
	
	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "メール形式で入力してください。")
	private String email;
	
	@NotBlank(message = "パスワードを入力してください。")
	@Length(min = 8, message = "8文字以上で入力してください。")
	private String password;
	
	@NotBlank(message = "確認のため、もう一度パスワードを入力してください。")
	private String passwordConfirmation;
}