package com.example.portfolio.model.form;

import lombok.Data;

@Data
public class SearchForm {
  private Integer categoryId; // intだと整数のみしか入らない値型であるため、null時条件無視するQBEが使えない
  private String productName;
}
