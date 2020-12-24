package com.example.portfolio.controller;

import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.form.UserForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @RestController : View に遷移せず、メソッドの戻り値がそのままレスポンスのコンテンツになる。
@Controllerでは@ResponseBodyを使用することで、同様の処理が可能だが、@RestControllerは@ResponseBodyを記述する必要なし。
通常は@Controllerを使用するが、JSON や XML などを返す WebAPI 用のControllerなどには@RestControllerを使用*/
@RequestMapping("/portfolio/auth")
@RestController
public class AuthController {

  private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); // Gsonは双方向の接続を持ったエンティティーのインスタンスをシリアライズしようとすると、それが無限に続くためstackOverFlowErrorが発生する。これを防ぐにはtoJson()で送りたいデータだけに@Exposeをつけて、Gsonのインスタンスの仕方をこのようにすればエラーが起きない

  @Autowired
  private UserService userService;

  @Autowired
  private LoginSession loginSession;

  /**
   * ログインメソッド
   * @param form
   * @param model
   * @return
   */
  @PostMapping("/login")
  public String login(@RequestBody UserForm form, Model model) { // @RequestBodyを付与することによって自動的にJSONデータをJavaで扱えるようにする
    User user = userService.findUser(form.getUserName(), form.getPassword()); // ログインフォームに入力されたユーザ名とパスワードと一致するユーザを取得

    if (user != null) { // ユーザが存在すれば
      loginSession.setTmpUserId(null); // トップページ初期表示時に付与した仮ユーザIDをnullにして破棄
      loginSession.setLogined(true); // ログイン状態にする
      loginSession.setUserId(user.getId());
      loginSession.setUserName(user.getUserName());
      loginSession.setPassword(user.getPassword());
    } else { // 一致するユーザ情報がなけらば、仮ユーザIDはそのまま
      loginSession.setLogined(false);
      loginSession.setUserId(null);
      loginSession.setUserName(null);
      loginSession.setPassword(null);
    }
    model.addAttribute("loginSession", loginSession);
    return gson.toJson(user);
  }

  /**
   * ログアウトメソッド
   * @return
   */
  @PostMapping("/logout")
  public String logout() {
    loginSession.setTmpUserId(null);
    loginSession.setLogined(false);
    loginSession.setUserId(null);
    loginSession.setUserName(null);
    loginSession.setPassword(null);
    return "";
  }

}
