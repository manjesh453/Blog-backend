package com.manjesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjesh.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
