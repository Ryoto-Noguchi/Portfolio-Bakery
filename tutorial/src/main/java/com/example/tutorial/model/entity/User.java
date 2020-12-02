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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mst_user")
@Data
public class User implements Serializable {
  private static final long serialVersionUID = 4867739830142183022L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "mail_address")
  private String mailAdress;

  @Column(name = "family_name")
  private String familyName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "family_name_kana")
  private String familyNameKana;

  @Column(name = "first_name_kana")
  private String firstNameKana;

  @Column(name = "gender")
  private int gender;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  @Column(name = "delete_flag")
  private boolean delete_flag;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private Destination destination;

  @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
  private List<PurchaseHistory> purchaseHistoryList;

}
