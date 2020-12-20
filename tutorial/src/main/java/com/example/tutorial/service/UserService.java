package com.example.tutorial.service;

import com.example.tutorial.model.dao.UserRepository;
import com.example.tutorial.model.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepos;

	public User findUser(String userName, String password) {
		return userRepos.findByUserNameAndPassword(userName, password);
	}

	public boolean findByUserName(String newUserName) {
		return userRepos.findByUserName(newUserName);
	}

}
