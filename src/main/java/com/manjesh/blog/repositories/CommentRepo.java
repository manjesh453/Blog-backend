package com.manjesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manjesh.blog.entities.Comment;
import com.manjesh.blog.entities.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
  
	@Query("SELECT c FROM Comment c WHERE c.post=:POST")
	List<Comment>findByPost(Post POST);
	
}
