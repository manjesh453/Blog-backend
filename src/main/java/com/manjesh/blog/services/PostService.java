package com.manjesh.blog.services;

import java.util.List;

import com.manjesh.blog.entities.Post;
import com.manjesh.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId,String fileName);
	
	PostDto updatePost(PostDto postDto,Integer postId,String fileName);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
    List<PostDto> getPostsByUser(Integer userId);
    
    List<PostDto> searchPosts(String keyword);
}
