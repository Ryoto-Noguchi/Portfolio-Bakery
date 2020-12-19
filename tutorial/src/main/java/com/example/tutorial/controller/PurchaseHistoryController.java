package com.example.tutorial.controller;

import java.util.List;

import com.example.tutorial.model.dto.PurchaseHistoryDto;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.PurchaseHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tutorial/history")
public class PurchaseHistoryController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private PurchaseHistoryService historyService;

  @GetMapping("/")
  public String goHistoryPage(Model model) {
    int userId = loginSession.getUserId();
    List<PurchaseHistoryDto> histories = historyService.findHistories(userId); // 購入履歴テーブルの全レコードをとってくる

    model.addAttribute("histories", histories);
    model.addAttribute("loginSession", loginSession);
    return "purchase_history";
  }

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
