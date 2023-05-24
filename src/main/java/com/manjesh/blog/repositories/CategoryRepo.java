package com.manjesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjesh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
