package com.example.tutorial.service;

import java.util.List;

import com.example.tutorial.model.dao.CartRepository;
import com.example.tutorial.model.dao.PurchaseHistoryRepository;
import com.example.tutorial.model.entity.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PurchaseHistoryService {

  @Autowired
  private PurchaseHistoryRepository historyRepos;

  @Autowired
  private CartRepository cartRepos;

  public int insert(int destinationId, int userId) {
    List<Cart> carts = cartRepos.findByUserIdAndDeleteFlagFalseOrderById(userId); // 論理削除されていないカートを取得
    System.out.println("カートの数 :" + carts.size());
    int result = 0;
    for (Cart cart : carts) {
      result += historyRepos.insert(cart, destinationId);
    }
    return result;
  }
}
