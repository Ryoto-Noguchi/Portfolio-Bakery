package com.example.tutorial.controller;

import com.example.tutorial.model.entity.User;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tutorial/mypage")
public class MyPageController {


  @Autowired
  private UserService userService;


  @Autowired
  private LoginSession loginSession;

  @GetMapping("/")
  public String goMyPage(Model model) {
    User user = userService.findUser(loginSession.getUserName(), loginSession.getPassword());
    model.addAttribute("user", user);
    return "my_page";
  }
}
