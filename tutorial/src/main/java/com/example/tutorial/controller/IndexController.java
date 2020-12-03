package com.example.tutorial.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.tutorial.model.entity.Category;
import com.example.tutorial.model.entity.Product;
import com.example.tutorial.model.form.SearchForm;
import com.example.tutorial.model.session.LoginSession;
import com.example.tutorial.service.CategoryService;
import com.example.tutorial.service.ProductService;

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
  CategoryService categoryService;

  @Autowired
  ProductService productService;

  @GetMapping(value = {"/", "/{page:^[1-9][0-9]*$}"})
  public String index(@ModelAttribute("searchForm") SearchForm searchForm, @PathVariable(name = "page") Optional<Integer> page, Model model) {
    if (loginSession.isLogined() == false) {
      int tempUserId = (int)(Math.random() * 1000000000);
      loginSession.setTmpUserId(tempUserId);
    }

    int currentPage = page.orElse(1); // 押下されたページリンクの数字(リクエストされたページ番号)
    if (currentPage == 0) {currentPage = 1;} // 先頭ページを表示している際の「<」押下用
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
    return "index";
  }
}
