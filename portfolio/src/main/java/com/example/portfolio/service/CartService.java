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

	/**
	 * ユーザ名と商品名からカートを取得
	 * @param userId
	 * @param productId
	 * @return
	 */
	public Cart findCart(int userId, int productId) {
		return cartRepos.findByUserIdAndProductIdAndDeleteFlagFalseOrderById(userId, productId);
	}

	/**
	 * カート更新
	 * @param cart
	 * @return
	 */
	public int updateCart(Cart cart) {
		return cartRepos.updateCart(cart);
	}

	/**
	 * カート追加
	 * @param cart
	 * @return
	 */
	public int insertCart(Cart cart) {
		return cartRepos.insertCart(cart);
	}

	/**
	 * ユーザIDを条件にカートを取得
	 * @param userId
	 * @return
	 */
	public List<Cart> findCartList(int userId) {
		return cartRepos.findByUserIdAndDeleteFlagFalseOrderById(userId);
	}

	/**
	 * カート削除
	 * @param id
	 * @return
	 */
	public int deleteCart(int id) {
		return cartRepos.logicalDeleteById(id);
	}

	/**
	 * ユーザIDを条件にカート削除
	 * @param userId
	 * @return
	 */
	public int deleteCartByUserId(int userId) {
		return cartRepos.logicalDeleteByUserId(userId);
	}

}
