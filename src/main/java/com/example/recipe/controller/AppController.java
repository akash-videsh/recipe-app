package com.example.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import com.example.recipe.model.*;
import com.example.recipe.repository.*;
import com.example.recipe.service.*;
import com.example.recipe.exception.*;

@SuppressWarnings("unused")
@Controller
public class AppController {

	@Autowired
	RecipeService rs;
	
	@Autowired
	IngredientService is;
	
	@Autowired
	CategoryService cs;
	
	@GetMapping("/homepage")
	public String showHomePage() {
		return "homepage";
	}
	
	
	@GetMapping("/recipe")
	public String ShowRecipePage(Model model) {
		List<Category> categories = cs.getAllCategories();
		model.addAttribute("categories",categories);
		model.addAttribute("recipe",new Recipe());
		return "addrecipe";
	}
	

	@PostMapping("/addRecipe")
	public String saveRecipe(@ModelAttribute Recipe recipe) {
		rs.saveRecipe(recipe);
		List<Ingredient> a =recipe.getIngredients();
		for(Ingredient c : a) {
			c.setRecipe(recipe);
			is.saveIngredient(c);
		}
		Category c = recipe.getCategory();
		c.getRecipes().add(recipe);
		cs.saveCategory(c);
		
		return "redirect:/homepage";
}
	@GetMapping("/category")
	public String showCategoryPage(Model model) {
		model.addAttribute("category",new Category());
		return "add-category";
	}
	
	@PostMapping("/addcategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		cs.saveCategory(category);
		return "redirect:/homepage";
	}
	
	@GetMapping("/showallrecipes")
	public String ShowAllRecipes (Model model) {
		List<Recipe> recipes = rs.getAllRecipes();
		model.addAttribute("recipes",recipes);
		return "showallrecipes";
	}
	
	@GetMapping("/getRecipebyCategory")
	public String getRecipebyCategory (Model model) {
		List<Category> categories = cs.getAllCategories();
		model.addAttribute("categories",categories);
		model.addAttribute("category", new Recipe());
		return "getCategory";
		
	}
	
	@PostMapping("/fetchRecipe")
	public String FetchRecipe(@ModelAttribute("category") Recipe recipe, Model model) {
		List<Recipe> a = recipe.getCategory().getRecipes();
		model.addAttribute("fetchedrecipes",a);
		return "ShowFetchedRecipes";
		
	}
	

	 @GetMapping("/getById")
	 public String showGetByIdForm() {
	        return "getById";
	    }

	 @GetMapping("/getRecipeById")
	 public String getRecipeById(@RequestParam("id") Long recipeId, Model model) {
	        Recipe recipe = rs.getRecipe(recipeId);
	        if (recipe != null) {
	            model.addAttribute("recipe", recipe);
	            return "recipeDetails";
	        } else {
	            model.addAttribute("error", "Recipe not found");
	            return "error";
	        }
	    }
	
	 @GetMapping("/removebyId")	 
	 public String showRemoveByIdForm() {
	        return "RemoveById";
	    }
	
	 @GetMapping("/removeRecipeById")
	 public String removeRecipeById(@RequestParam("id") Long recipeId, Model model) {
	        Recipe recipe = rs.getRecipe(recipeId);
	        if (recipe != null) {
	        	rs.removeRecipe(recipeId);
	            return "homepage";
	        } else {
	            model.addAttribute("error", "Recipe not found");
	            return "error";
	        }
	    }
	
	
}

