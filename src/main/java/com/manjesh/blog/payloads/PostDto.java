package com.manjesh.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date date;
	
	private UserDto user;
	
	private CategoryDto category;
		
	
	

}
