package com.example.portfolio.service;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;

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

	public int findByUserName(String newUserName) {
		return userRepos.findByUserName(newUserName);
	}

	public int insertUser(User user) {
		return userRepos.insert(user);
	}

}
