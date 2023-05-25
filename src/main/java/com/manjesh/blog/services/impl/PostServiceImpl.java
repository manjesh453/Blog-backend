package com.manjesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manjesh.blog.entities.Category;
import com.manjesh.blog.entities.Post;
import com.manjesh.blog.entities.User;
import com.manjesh.blog.exceptions.ResourceNotFoundException;
import com.manjesh.blog.payloads.PostDto;
import com.manjesh.blog.repositories.CategoryRepo;
import com.manjesh.blog.repositories.PostRepo;
import com.manjesh.blog.repositories.UserRepo;
import com.manjesh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
   @Autowired
	private PostRepo postRepo;
   @Autowired
   private ModelMapper modelMapper;
   @Autowired
   private UserRepo userRepo;
   @Autowired
   private CategoryRepo categoryRepo;
   
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId", categoryId));		
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
