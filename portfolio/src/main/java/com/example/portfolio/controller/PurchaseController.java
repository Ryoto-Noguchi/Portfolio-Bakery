package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.CartService;
import com.example.portfolio.service.DestinationService;
import com.example.portfolio.service.PurchaseHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portfolio/purchase")
public class PurchaseController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private DestinationService destinationService;

  @Autowired
  private CartService cartService;

  @Autowired
  private PurchaseHistoryService historyService;

  /**
   * 決済ページ初期表示メソッド
   * @param model
   * @param destination
   * @return
   */
  @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST}) // 宛先を削除した際にGETメソッドでページのリロードを行うため、POSTメソッドと両方に対応するようにする
  public String goPurchasePage(Model model, Destination destination) {
    int userId = loginSession.getUserId();
    List<Destination> destinations = destinationService.findDestination(userId);
    model.addAttribute("destinations", destinations);
    model.addAttribute("loginSession", loginSession);
    return "purchase";
  }

  /**
   * 宛先削除メソッド
   * @param destinationId
   * @return
   */
  @PostMapping("/delete")
  @ResponseBody
  public int deleteDestination(@RequestBody String destinationId) {
    int id = Integer.parseInt(destinationId);
    int result = destinationService.deleteDestination(id);
    return result;
  }

  /**
   * 決済処理メソッド
   */
  @PostMapping("/settle")
  @ResponseBody
  public int settle(@RequestBody String destinationId) {
    int result = historyService.insert(Integer.parseInt(destinationId), (int)loginSession.getUserId()); // destinationIdとuserIdに一致する(論理削除されていない)カートをtbl_purchase_hisotryに追加
    cartService.deleteCartByUserId(loginSession.getUserId()); // ログインしているユーザのカートを空にする
    return result;
  }

}
