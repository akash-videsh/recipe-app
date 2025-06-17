package com.example.recipe.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe.model.Ingredient;
import com.example.recipe.repository.IngredientRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class IngredientService {
	@Autowired
	private IngredientRepository ir;
	
	public void saveIngredient(Ingredient ingredient) {
		ir.save(ingredient);
	}
	
	public void getIngredient(Long id) {
		Ingredient ingredient = ir.findById(id).orElse(null);
        System.out.println("Ingredient: " + ingredient);
    }
	
}
