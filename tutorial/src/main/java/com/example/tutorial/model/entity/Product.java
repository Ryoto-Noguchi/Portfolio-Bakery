package com.example.tutorial.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.Data;

@Entity
@Table(name = "mst_product")
@Data
public class Product implements Serializable {
  private static final long serialVersionUID = -8078484477713715056L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "product_name_kana")
  private String productNameKana;

  @Column(name = "product_description")
  private String productDescription;

  @Column(name = "category_id")
  private int categoryId;

  @Column(name = "price")
  private int price;

  @Column(name = "image_full_path")
  private String imageFullPath;

  @Column(name = "release_date")
  private String releaseDate;

  @Column(name = "release_comapany")
  private String releaseCompany;

  @Column(name = "delete_flag")
  private boolean delete_flag;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @ManyToOne(fetch = FetchType.LAZY) // One-To-Many/Many-To-Oneの関係ではMany側が所有側になる
  @JoinColumn(name = "category_id", insertable = false, updatable = false) //@JoinColumnアノテーションは所有側に定義される。nameにはOne側と結合する際に必要となるカラム名をいれる。これを入力したら、One(被所有側)にmappedByをつける
  private Category category;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<Cart> cartList;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<PurchaseHistory> purchaseHistoryList;
}
