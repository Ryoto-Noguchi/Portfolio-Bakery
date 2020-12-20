package com.example.tutorial.service;

import java.util.List;

import com.example.tutorial.model.dao.CategoryRepository;
import com.example.tutorial.model.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepos;
	public List<Category> getCategories() {

		return categoryRepos.findAll();
	}

}
