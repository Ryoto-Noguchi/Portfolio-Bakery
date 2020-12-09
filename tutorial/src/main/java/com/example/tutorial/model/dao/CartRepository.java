package com.example.tutorial.model.dao;

import com.example.tutorial.model.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	int findByUserIdAndProductId(int userId, int productId);

}
