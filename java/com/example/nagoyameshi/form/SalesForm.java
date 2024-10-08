package com.example.nagoyameshi.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesForm {
	@NotNull(message = "店舗IDは必須です")
	private Integer shopId;
	
	@Min(value = 0, message = "0以上の値を入力してください")
	private Integer totalSales;
}