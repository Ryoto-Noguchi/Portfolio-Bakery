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

	Cart findByUserIdAndProductIdAndDeleteFlagFalseOrderById(int userId, int productId);

	@Modifying
	@Query(value = "UPDATE tbl_cart SET product_count = product_count + :#{#cart.productCount} WHERE user_id = :#{#cart.userId} AND product_id = :#{#cart.productId} AND delete_flag = false", nativeQuery = true)
	int updateCart(@Param("cart") Cart cart); // @Paramはパラメータを任意の文字列に指定してクエリの中で当てはめることができる。これによってリファクタリングなどでパラメーターの文字列が変わった場合も対応できる

	@Modifying
	@Query(value = "INSERT INTO tbl_cart (user_id, product_id, product_count) VALUES (:#{#cart.userId}, :#{#cart.productId}, :#{#cart.productCount})", nativeQuery = true)
	int insertCart(@Param("cart") Cart cart);

	List<Cart> findByUserIdAndDeleteFlagFalseOrderById(int userId);

	@Modifying
	@Query(value = "UPDATE tbl_cart SET delete_flag = TRUE WHERE id = :id", nativeQuery = true)
	int logicalDeleteById(@Param("id") int id);

	@Modifying
	@Query(value = "UPDATE tbl_cart SET delete_flag = TRUE WHERE user_id = :userId", nativeQuery = true)
	int logicalDeleteByUserId(@Param("userId") int userId);


}
