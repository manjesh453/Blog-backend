package com.manjesh.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
  private int id;
  @NotEmpty
  @Size(min = 4,message = "Name must be of min 4 character")
  private String name;
  
  @NotEmpty(message="Email must not be empty!!")
  @Email(message = "Email must be in proper formated")
  @Pattern(regexp = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
  private String email;
  
  @NotNull
  @Size(min = 8,max = 15,message = "The password must be in between 8 to 15 character")
  private String password;
  
  @NotNull
  private String about;
  
}
