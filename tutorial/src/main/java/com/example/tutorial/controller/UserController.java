package com.example.tutorial.controller;

import com.example.tutorial.model.entity.User;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.UserService;
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
@RequestMapping("/portfolio/user")
public class UserController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private UserService userService;

  Gson gson = new Gson();

  @GetMapping("/")
  public String goUserRegisterPage(Model model) {
    model.addAttribute("loginSession", loginSession);
    return "user_register";
  }

  @PostMapping("/check")
  @ResponseBody
  public boolean checkUserName(@RequestBody String newUserName) {
    // String str = newUserName;
    boolean bool = false;
    int count = userService.findByUserName(newUserName);
    if (count > 0) { // 同一のユーザ名が既に存在していたらtrueを返してエラーを表示
      bool = true;
    } else {
      bool = false; // 同一のユーザ名が存在していなければfalseを返して続行させる
    }
    return bool;
  }

  @PostMapping("/register")
  @ResponseBody
  public boolean registerUser(@RequestBody User user) {
    boolean result = false;
    return result;
  }

}
