package com.manjesh.blog.services;

import java.util.List;

import com.manjesh.blog.payloads.CommentDto;


public interface CommentService{
	
	CommentDto createCmt(CommentDto cmtDto,Integer postId);
	CommentDto updateCmt(CommentDto cmtDto,Integer cmtId,Integer postId);
	void deleteCmt(Integer cmtId);
	List<CommentDto>getAllCmt();
	List<CommentDto>getCmtByPost(Integer postId);

}
