package com.example.tutorial.controller;

import com.example.tutorial.model.entity.User;
import com.example.tutorial.model.form.UserForm;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.UserService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/* @RestController : View に遷移せず、メソッドの戻り値がそのままレスポンスのコンテンツになる。
@Controllerでは@ResponseBodyを使用することで、同様の処理が可能だが、@RestControllerは@ResponseBodyを記述する必要なし。
通常は@Controllerを使用するが、JSON や XML などを返す WebAPI 用のControllerなどには@RestControllerを使用*/
@RequestMapping("/tutorial/auth")
@RestController
public class AuthController {

  private Gson gson = new Gson();

  @Autowired
  private UserService userService;

  @Autowired
  private LoginSession loginSession;

  @PostMapping("/login")
  public String login(@RequestBody UserForm form) { // @RequestBodyを付与することによって自動的にJSONデータをJavaで扱えるようにする
    User user = userService.findUser(form.getUserName(), form.getPassword());

    if (user != null) {
      loginSession.setTmpUserId(0);
			loginSession.setLogined(true);
			loginSession.setUserId(user.getId());
			loginSession.setUserName(user.getUserName());
			loginSession.setPassword(user.getPassword());
    } else {
			loginSession.setLogined(false);
			loginSession.setUserId(null);
			loginSession.setUserName(null);
			loginSession.setPassword(null);
    }
    return gson.toJson(user);
  }

  @PostMapping("/logout")
  public String logout() {
    loginSession.setTmpUserId(0);
		loginSession.setLogined(false);
		loginSession.setUserId(0);
		loginSession.setUserName(null);
    loginSession.setPassword(null);
    return "";
  }

}
