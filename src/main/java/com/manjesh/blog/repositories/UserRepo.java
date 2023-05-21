package com.manjesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjesh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
   
}