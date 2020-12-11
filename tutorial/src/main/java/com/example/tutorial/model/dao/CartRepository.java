package com.example.tutorial.model.dao;

import java.util.List;

import com.example.tutorial.model.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	int findByUserIdAndProductId(int userId, int productId);

	@Modifying
	@Query(value = "UPDATE mst_cart SET product_counnt = product_count + :#{#cart.productCount} WEHRE user_id = :#{#cart.userId} AND product_id = :#{#cart.productId}", nativeQuery = true)
	int updateCart(@Param("cart") Cart cart);

	@Modifying
	@Query(value = "INSERT INTO mst_cart (user_id, product_id, product_count) VALUES (:#{#cart.userId}, :#{#cart.productId}, :#{#cart.productCount})", nativeQuery = true)
	int insertCart(@Param("cart") Cart cart);

	List<Cart> findByUserId(int userId);

}
