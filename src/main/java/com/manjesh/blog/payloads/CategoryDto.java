package com.manjesh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
private int categoryId;
@NotBlank(message="Blank Category Title isnot valid")
@Size(min=4,message="Title must be of more then 4 characters")
private String categoryTitle;

@NotBlank(message="Description mustnot be null")
@Size(min=10,message="The Description must be longer then 10 characters")
private String categoryDescription;

}
