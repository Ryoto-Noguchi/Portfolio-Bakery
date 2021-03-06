package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.dto.PurchaseHistoryDto;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.PurchaseHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portfolio/history")
public class PurchaseHistoryController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private PurchaseHistoryService historyService;

  /**
   * 購入履歴ページ初期表示メソッド
   */
  @GetMapping("")
  public String goHistoryPage(Model model) {
    int userId = loginSession.getUserId();
    List<PurchaseHistoryDto> histories = historyService.findHistories(userId); // 購入履歴テーブルの全レコードをとってくる
    model.addAttribute("histories", histories);
    model.addAttribute("loginSession", loginSession);
    return "purchase_history";
  }

  /**
   * 購入履歴削除メソッド
   * @param checkedIdList
   * @return
   */
  @PostMapping("/delete")
  @ResponseBody
  public int deleteHistory(@RequestBody String[] checkedIdList) {
    int result = 0;
    for (String id : checkedIdList) { // 配列を拡張for文でループしてそれぞれDBから論理削除する
      result += historyService.deleteHistory(Integer.parseInt(id)); // パラメータの型はStringであるためparseIntする
    }
    return result;
  }

}
