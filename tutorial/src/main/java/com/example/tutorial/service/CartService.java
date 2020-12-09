package com.example.tutorial.service;

import com.example.tutorial.model.dao.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CartService {

  @Autowired
  private CartRepository cartRepos;

	public int findCart(int userId, int productId) {
		return cartRepos.findByUserIdAndProductId(userId, productId);
	}

}
