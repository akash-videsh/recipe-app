package com.example.recipe.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe.model.Category;
import com.example.recipe.repository.CategoryRepository;

import jakarta.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class CategoryService{
	@Autowired
	private CategoryRepository cr;
	
	public void saveCategory(Category category) {
		cr.save(category);
	}
	
	public Category getCategory(Long id) {
		Category category = cr.findById(id).orElse(null);
        System.out.println("Category: " + category);
        return category;
    }
	
	public List<Category> getAllCategories(){
		return cr.findAll();
	}
	
}
