package com.manjesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manjesh.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
