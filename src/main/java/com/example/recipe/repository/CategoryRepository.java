package com.example.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.recipe.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
