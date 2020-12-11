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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.tutorial.model.session.SearchSession;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mst_product")
@Getter @Setter @NoArgsConstructor
public class Product implements Serializable {

  private static final long serialVersionUID = -8078484477713715056L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id; // intだと整数のみしか入らない値型であるため、null時条件無視するQBEが使えない

  @Column(name = "product_name")
  private String productName;

  @Column(name = "product_name_kana")
  private String productNameKana;

  @Column(name = "product_description")
  private String productDescription;

  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "price")
  private Integer price;

  @Column(name = "image_full_path")
  private String imageFullPath;

  @Column(name = "release_date")
  private String releaseDate;

  @Column(name = "release_company")
  private String releaseCompany;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  //@JoinColumnアノテーションは所有側に定義される。nameにはOne側と結合する際に必要となるカラム名をいれる。これを入力したら、One(被所有側)にmappedByをつける
  @ManyToOne(fetch = FetchType.LAZY) // One-To-Many/Many-To-Oneの関係ではMany側が所有側になる
  @JoinColumn(name = "category_id", insertable = false, updatable = false)
  private Category category;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<Cart> cartList;

  @ManyToMany
  @JoinColumn(name = "id")
  private List<PurchaseHistory> purchaseHistoryList;

  public Product(SearchSession searchSession) {
    this.categoryId = searchSession.getCategoryId();
    this.productName = searchSession.getProductName();
  }

  public Product(String productName) {
    this.productName = productName;
  }

}
