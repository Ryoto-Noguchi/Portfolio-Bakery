package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(value = "SELECT product_name FROM mst_product WHERE id = :productId", nativeQuery = true)
	String findProductNameById(int productId);

}
