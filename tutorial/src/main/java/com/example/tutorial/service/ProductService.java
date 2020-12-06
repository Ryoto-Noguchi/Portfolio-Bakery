package com.example.tutorial.service;

import com.example.tutorial.model.dao.ProductRepository;
import com.example.tutorial.model.entity.Product;
import com.example.tutorial.model.form.SearchForm;
import com.example.tutorial.model.session.SearchSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductService {

  @Autowired
  SearchSession searchSession;

  @Autowired
  ProductRepository productRepos;

  public Page<Product> productSearch(SearchForm searchForm, Pageable pageable) {
    ExampleMatcher customExampleMatcher = ExampleMatcher.matching().withMatcher("productName",
        ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    SearchForm sample = new SearchForm();
    if (searchForm.equals(sample)) {
      System.out.println("検索窓は全て空欄でした");
    } else {
      searchSession.setCategoryId(searchForm.getCategoryId());
      searchSession.setProductName(searchForm.getProductName());
    }
    Product product = new Product(searchSession);
    Example<Product> example = Example.of(product, customExampleMatcher);
    Page<Product> products = productRepos.findAll(example, pageable);
    return products;
  }

}
