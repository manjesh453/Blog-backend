package com.manjesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjesh.blog.payloads.ApiResponse;
import com.manjesh.blog.payloads.CommentDto;
import com.manjesh.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

		@Autowired
		private CommentService cmtService;
		
		
		@PostMapping("/create/{postId}")
		public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto cmtDto,@PathVariable Integer postId){
			CommentDto newcmt=this.cmtService.createCmt(cmtDto,postId);
			return new ResponseEntity<CommentDto>(newcmt,HttpStatus.CREATED);
		}
		
		@PutMapping("/post/{postId}/cmt/{cmtId}")
		public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto cmtDto,@PathVariable Integer cmtId,@PathVariable Integer postId){
		CommentDto updatecmt=this.cmtService.updateCmt(cmtDto, cmtId, postId);
		return new ResponseEntity<CommentDto>(updatecmt,HttpStatus.OK);
		}
		@DeleteMapping("/{cmtId}")
		public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer cmtId) {
			this.cmtService.deleteCmt(cmtId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
		}
		
		@GetMapping("/{postId}")
		public ResponseEntity<List<CommentDto>>getCommentByPost(@PathVariable Integer postId){
			List<CommentDto> cat=this.cmtService.getCmtByPost(postId);
			return ResponseEntity.ok(cat);
			
		}
		@GetMapping("/")
		public ResponseEntity<List<CommentDto>> getAllComment(){
			List<CommentDto> cat=this.cmtService.getAllCmt();
			return ResponseEntity.ok(cat);
			
		}



}
