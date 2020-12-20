package com.example.portfolio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.Product;
import com.example.portfolio.model.form.SearchForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.model.session.SearchSession;
import com.example.portfolio.service.CategoryService;
import com.example.portfolio.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

  @Autowired
  LoginSession loginSession;

  @Autowired
  SearchSession searchSession;

  @Autowired
  CategoryService categoryService;

  @Autowired
  ProductService productService;

  /**
   * トップページへ遷移するメソッド
   * @return
   */
  @GetMapping("/refresh")
  public String refresh() {
    searchSession.setCategoryId(null);
    searchSession.setProductName("");
    return "redirect:/index";
  }

  /**
   * トップページの初期表示と検索時、ページ番号押下時の商品リスト表示
   * @param page
   * @param model
   * @return リクエストされたページの資産リスト
   */
  @GetMapping(value = { "", "/{page:^[1-9][0-9]*$}" })
  public String index(@ModelAttribute("searchForm") SearchForm searchForm, @PathVariable(name = "page") Optional<Integer> page, Model model) {
    if (loginSession.isLogined() == false && loginSession.getTmpUserId() == null) { // ログインしてない&仮ユーザIDがない(=初めてページを開いたとき)
      int tempUserId = (int) (Math.random() * 1000000000);
      loginSession.setTmpUserId(tempUserId); // ランダムな整数を仮ユーザIDとしてログインセッションに登録
    }

    int currentPage = page.orElse(1); // 押下されたページリンクの数字(リクエストされたページ番号)
    if (currentPage == 0) {
      currentPage = 1;
    } // 先頭ページを表示している際の「<」押下用
    Sort sort = Sort.by("id").ascending(); // ソートのルールを作成
    Pageable pageable = PageRequest.of(currentPage - 1, 5, sort); // ページネーション情報作成
    Page<Product> products = productService.productSearch(searchForm, pageable);
    model.addAttribute("products", products);

    int totalPages = products.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList()); // HTMLでページ分ループするために各ページ番号が入ったリストを作成
      model.addAttribute("pageNumbers", pageNumbers);
    }

    List<Category> categories = categoryService.getCategories();
    model.addAttribute("categories", categories);
    model.addAttribute("loginSession", loginSession);
    if (searchSession.getCategoryId() != null) { model.addAttribute("categoryId", searchSession.getCategoryId()); }
    if (searchSession.getProductName() != null) { model.addAttribute("productName", searchSession.getProductName()); }
    return "index";
  }

  @GetMapping("/product/{id}")
  public String goProductDetail(@PathVariable("id") int id, Model model) {
    Product product = productService.findOneProduct(id);
    model.addAttribute("product", product);
    return "product_detail";

  }
}
