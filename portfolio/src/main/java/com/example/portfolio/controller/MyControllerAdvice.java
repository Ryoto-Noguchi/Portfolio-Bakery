package com.example.portfolio.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

  @ExceptionHandler({ Exception.class })
  public String handleException(Exception e, HttpServletResponse response, Model model) {
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    model.addAttribute("errorMessage", e.getMessage());
    return "error.html";
  }
}
