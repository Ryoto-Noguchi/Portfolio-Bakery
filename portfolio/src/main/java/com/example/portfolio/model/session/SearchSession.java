package com.example.tutorial.model.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class SearchSession {
  private Integer categoryId;
  private String productName;
}
