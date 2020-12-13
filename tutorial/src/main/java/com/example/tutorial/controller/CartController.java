package com.example.tutorial.controller;

import java.util.List;
// import java.util.Map;

import com.example.tutorial.model.entity.Cart;
import com.example.tutorial.model.form.CartForm;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.CartService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/")
  public String goCartPage(Model model, Cart cart) { // 引数にCartインスタンスを入れないとcart.htmlのth:each="cart:${cartList}"の箇所でNoBindingResultエラーが出る
    int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
    List<Cart> cartList = cartService.findCartList(userId);
    model.addAttribute("cartList", cartList);
    model.addAttribute("loginSession", loginSession);
    return "cart";
  }

  @PostMapping("/add")
  @ResponseBody
  public String add(@RequestBody CartForm form) {
    int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId(); // ユーザID or 仮ユーザIDをユーザIDとする
    int productId = form.getProductId();
    form.setUserId(userId);

    Cart cart = new Cart(form); // 新しいカートのレコードをmst_cartテーブルに追加

    if (cartService.findCart(userId, productId) != null) { // あるユーザが今回追加した商品をすでにカートに入れていたら
      int result = cartService.updateCart(cart);
      System.out.println("カートを" + result + "件更新しました");
    } else {
      int result = cartService.insertCart(cart);
      System.out.println("カートを" + result + "件追加しました");
    }
    return gson.toJson(cart);
  }
  
  @PostMapping("/delete")
  @ResponseBody
  public int deleteCart(@RequestBody String[] checkedIdList) { // ☑️は複数選択可能なため、配列でデータを渡すため、型はString[]
    int result = 0;
    for (String id : checkedIdList) { // 配列を拡張for文でループしてそれぞれDBから論理削除する
      result += cartService.deleteCart(Integer.parseInt(id)); // パラメータの型はStringであるためparseIntする
    }
    return result;
    /*
    ⇩のようにしてMapを使って処理することを可能。その場合はcat.htmlのajaxの条件定義で「data: JSON.stringify({ 'checkedIdList': checkedIdList })」のようにして、keyとvalueを指定しないとダメ
      Map<String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
      List<String> checkedIds = map.get("checkedIdList");
      for (String id : checkedIds) {
        result += cartService.deleteCart(Integer.parseInt(id));
      }
    */
  }
}
