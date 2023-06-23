package com.manjesh.blog.payloads;

import lombok.Data;

@Data
public class CommentDto {
	
	private Integer id;
	
	private String cmtName;
	
	private PostDto postDto;

}
