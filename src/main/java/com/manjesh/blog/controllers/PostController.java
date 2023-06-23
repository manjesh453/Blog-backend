package com.manjesh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.manjesh.blog.payloads.ApiResponse;
import com.manjesh.blog.payloads.PostDto;
import com.manjesh.blog.services.FileService;
import com.manjesh.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(@RequestParam("image")MultipartFile image,@RequestPart PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId) throws IOException{
		String filename=this.fileService.uploadImage(path, image);
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId,filename);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<PostDto>>findByKeyword(@PathVariable String keyword){
		return ResponseEntity.ok(this.postService.searchPosts(keyword));
	}
    
	@PutMapping("/post/{postId}/")
	public ResponseEntity<PostDto>updatePost(@RequestParam("image")MultipartFile image,@RequestPart PostDto postDto,@PathVariable Integer postId) throws IOException{
		String filename=this.fileService.uploadImage(path, image);
		PostDto updatePost=this.postService.updatePost(postDto, postId,filename);
		return ResponseEntity.ok(updatePost);
	}
	
	@DeleteMapping("/post/{postId}/")
	public ResponseEntity<?>deletePost(@PathVariable Integer postId){
	this.postService.deletePost(postId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("Post Delete Successfully",true),HttpStatus.OK);
	}
	@GetMapping("/post/{postId}/")
	public ResponseEntity<PostDto>getById(@PathVariable Integer postId){
		PostDto post=this.postService.getPostById(postId);
		return ResponseEntity.ok(post);
	}
	@GetMapping("/post/")
	public ResponseEntity<List<PostDto>>getAll(){
		return ResponseEntity.ok(this.postService.getAllPost());
	}
	@GetMapping("/cat/{categoryId}/post/")
	public ResponseEntity<List<PostDto>>getByCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.postService.getPostsByCategory(categoryId));
	}
	@GetMapping("/user/{userId}/post/")
	public ResponseEntity<List<PostDto>>getByUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.postService.getPostsByUser(userId));
	}
	@GetMapping("/{keyword}/")
	public ResponseEntity<List<PostDto>>searchPost(@PathVariable String keyword){
		return ResponseEntity.ok(this.postService.searchPosts(keyword));
	}
	@GetMapping(value = "/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE )
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response)throws IOException {
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	} 
}







