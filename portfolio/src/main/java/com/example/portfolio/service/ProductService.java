package com.example.portfolio.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.portfolio.model.dao.ProductRepository;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.form.SearchForm;
import com.example.portfolio.model.session.SearchSession;

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

  /**
   * クエストされたページ番号に応じたページをフェッチするメソッド
   *
   * @param searchForm
   * @param pageable
   * @return
   */
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

  /**
   * 商品番号をもとに商品のレコードをフェッチしてくるメソッド
   *
   * @param id
   * @return
   */
  public Product findOneProduct(int id) throws NoSuchElementException {
    Optional<Product> fetchedProduct = productRepos.findById(id);
    Product product = null;
    try {
      product = fetchedProduct.orElseThrow(); // 該当する商品が見つからなかった場合はエラーを投げる
    } catch (NoSuchElementException e) {
      throw new RuntimeException(e);
    }
    return product;
  }

public String findProductNameByProductId(int productId) {
	return productRepos.findProductNameById(productId);
}

}
