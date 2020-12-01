package com.example.tutorial.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_purchase_hitory")
@Data
public class PurchaseHistory implements Serializable {
  private static final long serialVersionUID = -514891244709797659L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "product_id")
  private int productId;

  @Column(name = "product_count")
  private int productCount;

  @Column(name = "price")
  private int price;

  @Column(name = "destination_id")
  private int destinationId;

  @Column(name = "status")
  private int status;

  @Column(name = "purchased_at")
  private Timestamp purchasedAt;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "upated_at")
	private Timestamp updatedAt;
}
