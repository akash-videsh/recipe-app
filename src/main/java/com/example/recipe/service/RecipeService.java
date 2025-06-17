package com.example.recipe.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe.model.Category;
import com.example.recipe.model.Recipe;
import com.example.recipe.repository.CategoryRepository;
import com.example.recipe.repository.RecipeRepository;

import jakarta.transaction.Transactional;


@SuppressWarnings("unused")
@Service
@Transactional
public class RecipeService {
	@Autowired
	private RecipeRepository rr;
	
	public void saveRecipe( Recipe recipe) {
		//Category category = cr.findById(c1.getId()).orElseThrow();
     //   recipe.setCategory(c1);
         rr.save(recipe);
		//rr.saveAndFlush(recipe);
	}
	
	public Recipe getRecipe(Long id) {
		Recipe recipe = rr.findById(id).orElse(null);
        return recipe;
    }
	
	public List<Recipe> getAllRecipes(){
		return rr.findAll();
	}
	
	public void removeRecipe(Long id) {
		rr.deleteById(id);
	}
}
