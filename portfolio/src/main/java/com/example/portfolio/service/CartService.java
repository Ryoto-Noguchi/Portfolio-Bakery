package com.example.portfolio.service;

import java.util.List;

import com.example.portfolio.model.dao.CartRepository;
import com.example.portfolio.model.entity.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CartService {

  @Autowired
  private CartRepository cartRepos;

	public Cart findCart(int userId, int productId) {
		return cartRepos.findByUserIdAndProductIdAndDeleteFlagFalseOrderById(userId, productId);
	}

	public int updateCart(Cart cart) {
		return cartRepos.updateCart(cart);
	}

	public int insertCart(Cart cart) {
		return cartRepos.insertCart(cart);
	}

	public List<Cart> findCartList(int userId) {
		return cartRepos.findByUserIdAndDeleteFlagFalseOrderById(userId);
	}

	public int deleteCart(int id) {
		return cartRepos.logicalDeleteById(id);
	}

	public int deleteCartByUserId(int userId) {
		return cartRepos.logicalDeleteByUserId(userId);
	}

}