package com.manjesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manjesh.blog.entities.Category;
import com.manjesh.blog.entities.Post;
import com.manjesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% ")
	List<Post>findByKeyword(String keyword);
	
}
