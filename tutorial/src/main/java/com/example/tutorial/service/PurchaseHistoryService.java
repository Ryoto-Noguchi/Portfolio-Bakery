package com.example.tutorial.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

  /**
   * 購入履歴テーブルにない商品名とアドレス情報を結合してhistoryDtoとしてControllerに返すメソッド
   * @param userId
   * @return 画面に表示するために必要な購入履歴情報
   */
  public List<PurchaseHistoryDto> findHistories(int userId) {

    // 論理削除されていない購入履歴を取得してくる
    List<PurchaseHistory> purchaseHistories = historyRepos.findByUserIdAndDeleteFlagFalse(userId);

    List<PurchaseHistoryDto> historyList = new ArrayList<PurchaseHistoryDto>();
    for (int i = 0; i < purchaseHistories.size(); i++) {

      // Timestamp型をString型でyyyy/mm/ddの形に整形
      Timestamp purchased_at = purchaseHistories.get(i).getPurchasedAt();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      String purchasedAt = sdf.format(purchased_at);

      // newしたPurchaseHistoryDtoインスタンスに必要な項目を1つずつ入れていく。
      PurchaseHistoryDto historyDto = new PurchaseHistoryDto();
      historyDto.setPurchasedAt(purchasedAt);
      historyDto.setProductName(productService.findProductNameByProductId(purchaseHistories.get(i).getProductId()));
      historyDto.setPrice(purchaseHistories.get(i).getPrice());
      historyDto.setProductCount(purchaseHistories.get(i).getProductCount());
      historyDto.setFamilyName(purchaseHistories.get(i).getUser().getFamilyName());
      historyDto.setFirstName(purchaseHistories.get(i).getUser().getFirstName());
      historyDto.setAddress(destinationRepos.findAddressById(purchaseHistories.get(i).getDestinationId()));
      historyList.add(historyDto);
    }
    return historyList;
  }

}
