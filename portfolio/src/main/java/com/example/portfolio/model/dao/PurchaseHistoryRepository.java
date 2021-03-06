package com.example.portfolio.model.dao;

import java.util.List;

import com.example.portfolio.model.entity.Cart;
import com.example.portfolio.model.entity.PurchaseHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

  @Modifying
  @Query(value = "INSERT INTO tbl_purchase_history (user_id, product_id, product_count, price, destination_id) VALUES (:#{#cart.userId}, :#{#cart.productId}, :#{#cart.productCount}, :#{#cart.product.price}, :destinationId)", nativeQuery = true)
  int insert(@Param("cart") Cart cart, @Param("destinationId") int destinationId); // エンティティのフィールドの中でオブジェクト(この例ではcartインスタンス)のものがあった場合に:#{#cart.product.price}のように「.」で繋げることでパラメーターを設定できる

  List<PurchaseHistory> findByUserIdAndDeleteFlagFalse(int userId);

  @Modifying
	@Query(value = "UPDATE tbl_purchase_history SET delete_flag = TRUE WHERE id = :id", nativeQuery = true)
  int logicalDeleteById(int id);

}
