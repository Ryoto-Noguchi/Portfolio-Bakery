package com.example.tutorial.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_purchase_history")
@Getter @Setter
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

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @Column(name = "purchased_at")
  private Timestamp purchasedAt;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToMany(mappedBy = "purchaseHistoryList")
  private List<Product> productList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_id", insertable = false, updatable = false)
  private Destination destination;

}
