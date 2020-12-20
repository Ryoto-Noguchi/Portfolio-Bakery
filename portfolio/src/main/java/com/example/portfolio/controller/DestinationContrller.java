package com.example.portfolio.controller;

import java.util.Optional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.DestinationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/portfolio/destination")
public class DestinationContrller {

  @Autowired
  LoginSession loginSession;

  @Autowired
  DestinationService destinationService;

  @Autowired
  UserRepository userRepos;

  @GetMapping("")
  public String goDestinationRegisterPage(Model model) {
    Optional<User> user = userRepos.findById(loginSession.getUserId());
    model.addAttribute("user", user);
    model.addAttribute("loginSession", loginSession);
    return "destination";
  }

  @PostMapping("/register")
  @ResponseBody
  public int insertDestination(@RequestBody Destination destination) {
    destination.setUserId(loginSession.getUserId());
    int result = destinationService.insertDestination(destination);
    System.out.println(result + "件の宛先を登録しました");
    return result;

  }
}
