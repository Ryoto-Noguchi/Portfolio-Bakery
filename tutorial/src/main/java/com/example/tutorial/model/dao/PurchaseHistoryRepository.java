package com.example.tutorial.model.dao;

// import java.util.List;

import com.example.tutorial.model.entity.Cart;
import com.example.tutorial.model.entity.PurchaseHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

  @Modifying
  @Query(value = "INSERT INTO tbl_purchase_history (user_id, product_id, product_count, price, destination_id) SELECT cart.user_id, cart.product_id, cart.product_count, product.price, destination.id FROM tbl_cart AS cart INNER JOIN mst_product AS product ON cart.product_id = product.id INNER JOIN mst_destination AS destination ON cart.user_id = destination.user_id WHERE cart.user_id = :#{#cart.userId} AND destination.id = :destinationId AND cart.delete_flag = false", nativeQuery = true)
  int insert(@Param("cart") Cart cart, @Param("destinationId") int destinationId);
  // TODO insert件数が想定よりも倍になってしまうバグ


}
