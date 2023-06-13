package com.manjesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manjesh.blog.entities.Category;
import com.manjesh.blog.entities.Post;
import com.manjesh.blog.entities.User;
import com.manjesh.blog.exceptions.ResourceNotFoundException;
import com.manjesh.blog.payloads.CategoryDto;
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
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		Post p=this.modelMapper.map(postDto, Post.class);
		post.setCategory(p.getCategory());
		post.setContent(p.getContent());
		post.setImageName(p.getImageName());
		post.setCategory(p.getCategory());
		Post updatePost=this.postRepo.save(post);	
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		this.postRepo.deleteById(postId);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post>list=this.postRepo.findAll();
		List<PostDto>post=list.stream().map((post1)->this.modelMapper.map(post1, PostDto.class)).collect(Collectors.toList());
		return post;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		List<Post>list=this.postRepo.findByCategory(cat);
		List<PostDto>post=list.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return post;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		List<Post>list=this.postRepo.findByUser(user);
		List<PostDto>post=list.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return post;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
