package com.example.tutorial.controller;

import java.util.List;

import com.example.tutorial.model.entity.Destination;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.DestinationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tutorial/purchase")
public class PurchaseController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private DestinationService destinationService;

  @PostMapping("/")
  public String goPurchasePage(Model model, Destination destination) {
    int userId = loginSession.getUserId();
    List<Destination> destinations = destinationService.findDestination(userId);
    model.addAttribute("destinations", destinations);
    model.addAttribute("loginSession", loginSession);
    return "purchase";
  }

}
