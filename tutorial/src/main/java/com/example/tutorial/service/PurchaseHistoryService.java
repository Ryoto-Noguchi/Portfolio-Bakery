package com.example.tutorial.service;

import java.util.ArrayList;
import java.util.List;

import com.example.tutorial.model.dao.CartRepository;
import com.example.tutorial.model.dao.DestinationRepository;
import com.example.tutorial.model.dao.PurchaseHistoryRepository;
import com.example.tutorial.model.dto.PurchaseHistoryDto;
import com.example.tutorial.model.entity.Cart;
import com.example.tutorial.model.entity.PurchaseHistory;

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

  @Autowired
  private ProductService productService;

  @Autowired
  private DestinationRepository destinationRepos;

  public int insert(int destinationId, int userId) {
    List<Cart> carts = cartRepos.findByUserIdAndDeleteFlagFalseOrderById(userId); // 論理削除されていないカートを取得
    System.out.println("カートの数 :" + carts.size());
    int result = 0;
    for (Cart cart : carts) {
      result += historyRepos.insert(cart, destinationId);
      System.out.println(result + "件追加しました");
    }
    return result;
  }

  public List<PurchaseHistoryDto> findHistories(int userId) {

    List<PurchaseHistory> purchaseHistories = historyRepos.findByUserIdAndDeleteFlagFalse(userId);
    List<PurchaseHistoryDto> historyList = new ArrayList<PurchaseHistoryDto>();
    for (int i = 0; i < purchaseHistories.size(); i++) {
        PurchaseHistoryDto historyDto = new PurchaseHistoryDto();
        historyDto.setPurchasedAt(purchaseHistories.get(i).getPurchasedAt().toString());
        historyDto.setProductName(productService.findProductNameByProductId(purchaseHistories.get(i).getProductId()));
        historyDto.setPrice(purchaseHistories.get(i).getPrice());
        historyDto.setProductCount(purchaseHistories.get(i).getProductCount());
        historyDto.setFamilyName(purchaseHistories.get(i).getUser().getFamilyName());
        historyDto.setFirstName(purchaseHistories.get(i).getUser().getFirstName());
        historyDto.setAddress(destinationRepos.findAddressById(purchaseHistories.get(i).getDestinationId()));
      // for (PurchaseHistory purchaseHistory : purchaseHistories) {
      //   PurchaseHistoryDto historyDto = new PurchaseHistoryDto();
      //   historyDto.setPurchasedAt(purchaseHistory.getPurchasedAt().toString());
      //   historyDto.setProductName(productService.findProductNameByProductId(purchaseHistory.getProductId()));
      //   historyDto.setPrice(purchaseHistory.getPrice());
      //   historyDto.setProductCount(purchaseHistory.getProductCount());
      //   historyDto.setFamilyName(purchaseHistory.getUser().getFamilyName());
      //   historyDto.setFirstName(purchaseHistory.getUser().getFirstName());
      //   historyList.add(i, historyDto);
      // }
      historyList.add(historyDto);
    }
    return historyList;
  }

}
