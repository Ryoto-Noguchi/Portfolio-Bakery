package com.example.tutorial.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "mst_category")
@Getter @Setter // JPAのEntityには＠Dataは使わない方が良いらしい
public class Category implements Serializable {
  private static final long serialVersionUID = 7548736863062877284L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "category_description")
  private String categoryDescription;

  @Column(name = "delete_flag")
  private boolean deleteFlag;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
	private Timestamp updatedAt;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // mappedByには所有側の持つこのエンティティのインスタンス名をいれる
  private List<Product> productList;
}
