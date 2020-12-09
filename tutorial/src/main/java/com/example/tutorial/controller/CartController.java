package com.example.tutorial.controller;

import com.example.tutorial.model.entity.Cart;
import com.example.tutorial.model.form.CartForm;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.CartService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tutorial/cart")
public class CartController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private CartService cartService;

  Gson gson = new Gson();

  @PostMapping("/add")
  @ResponseBody
  public String add(@RequestBody CartForm form) {
    int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
    int productId = form.getProductId();
    form.setUserId(userId);

    Cart cart = new Cart();
    int result = 0;
    if (cartService.findCart(userId, productId) > 0) {
      
    } else {

    }

    return "";
  }
}
