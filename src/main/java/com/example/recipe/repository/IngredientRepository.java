package com.example.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.recipe.model.Ingredient;



@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
