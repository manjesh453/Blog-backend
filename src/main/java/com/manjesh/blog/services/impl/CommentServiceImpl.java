package com.manjesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjesh.blog.entities.Comment;
import com.manjesh.blog.entities.Post;
import com.manjesh.blog.exceptions.ResourceNotFoundException;
import com.manjesh.blog.payloads.CommentDto;
import com.manjesh.blog.repositories.CommentRepo;
import com.manjesh.blog.repositories.PostRepo;
import com.manjesh.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo cmtRepo;

	@Override
	public CommentDto createCmt(CommentDto cmtDto, Integer postId) {
		Comment cmt=this.modelMapper.map(cmtDto, Comment.class);
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
        cmt.setPost(post);
        Comment newCmt=this.cmtRepo.save(cmt);
		return this.modelMapper.map(newCmt, CommentDto.class);
	}

	@Override
	public CommentDto updateCmt(CommentDto cmtDto, Integer cmtId, Integer postId) {
		Comment cmt=this.cmtRepo.findById(cmtId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId",cmtId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
	    cmt.setCmtName(cmtDto.getCmtName());
	    cmt.setPost(post);
	    Comment update=this.cmtRepo.save(cmt);
	    return this.modelMapper.map(update, CommentDto.class);
	}

	@Override
	public void deleteCmt(Integer cmtId) {
		this.cmtRepo.deleteById(cmtId);
		
	}

	@Override
	public List<CommentDto> getAllCmt() {
		List<Comment>list=this.cmtRepo.findAll();
		List<CommentDto>cmt=list.stream().map(comm->this.cmtTODto(comm)).collect(Collectors.toList());
		return cmt;
	}

	@Override
	public List<CommentDto> getCmtByPost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		List<Comment>list=this.cmtRepo.findByPost(post);
		List<CommentDto>cmt=list.stream().map(coment->this.cmtTODto(coment)).collect(Collectors.toList());
		return cmt;
	}
	
	private CommentDto cmtTODto(Comment cmt) {
		return this.modelMapper.map(cmt, CommentDto.class);
	}

}
