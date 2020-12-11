package com.example.tutorial.model.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartForm implements Serializable {

  private static final long serialVersionUID = 6925524920600121727L;

  private Integer userId;
  private Integer productId;
  private Integer productCount;
  private String productName;
}
