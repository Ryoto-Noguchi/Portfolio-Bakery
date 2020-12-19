package com.example.tutorial.controller;

import java.util.List;

import com.example.tutorial.model.dto.PurchaseHistoryDto;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.PurchaseHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
