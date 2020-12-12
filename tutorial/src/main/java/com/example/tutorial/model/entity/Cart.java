package com.example.tutorial.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.tutorial.model.form.CartForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_cart")
@Getter
@Setter
@NoArgsConstructor
public class Cart implements Serializable {

  private static final long serialVersionUID = -4731172952052584718L;

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

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
  private Product product;

  public Cart(CartForm form) {
    this.id = form.getUserId();
    this.userId = form.getUserId();
    this.productId = form.getProductId();
    this.productCount = form.getProductCount();
    System.out.println("商品名" + form.getProductName());
    this.product = new Product(form.getProductName());
  }
}
