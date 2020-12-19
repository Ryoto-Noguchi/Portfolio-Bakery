package com.example.tutorial.model.dto;

import lombok.Data;

@Data
public class PurchaseHistoryDto {
	private Integer purchaseHistoryId;
  private String purchasedAt;
	private String productName;
	private int price;
	private int productCount;
	private String familyName;
	private String firstName;
	private String address;
}
