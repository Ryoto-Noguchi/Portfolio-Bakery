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
@Table(name = "mst_destination")
@Data
public class Destination implements Serializable {
  private static final long serialVersionUID = 6490324588125996050L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "family_name")
  private String familyName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "tel_number")
  private String telNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "status")
  private int status;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
	private Timestamp updatedAt;
}
