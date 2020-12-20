package com.example.tutorial.model.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;



@Component
@SessionScope
@Data
public class LoginSession implements Serializable {
  private static final long serialVersionUID = 8679121968320842070L;

  private Integer userId;
	private Integer tmpUserId;
	private String userName;
	private String password;
	private boolean logined;


}
