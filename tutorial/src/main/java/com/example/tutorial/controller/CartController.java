package com.example.tutorial.controller;

import java.util.List;

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

    Cart cart = new Cart(form); // 新しいカートのレコードをmst_cartテーブルに追加
    if (cartService.findCart(userId, productId) > 0) { // あるユーザが今回追加した商品をすでにカートに入れていたら
      int result = cartService.updateCart(cart);
      System.out.println("カートを" + result + "件更新しました");
    } else {
      int result = cartService.insertCart(cart);
      System.out.println("カートを" + result + "件追加しました");
    }

    List<Cart> cartList = cartService.findCartList(userId);
    return gson.toJson(cartList);
  }
}
