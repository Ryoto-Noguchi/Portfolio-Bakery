package com.example.portfolio.model.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserForm implements Serializable {

  private static final long serialVersionUID = -774806251322903986L;

  private String userName;
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private Integer gender;
	private String password;
	private String newPassword;
	private String newPasswordConfirm;
}
