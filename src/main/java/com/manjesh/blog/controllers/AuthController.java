package com.manjesh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjesh.blog.exceptions.ApiException;
import com.manjesh.blog.payloads.JwtAuthRequest;
import com.manjesh.blog.payloads.JwtAuthResponse;
import com.manjesh.blog.payloads.UserDto;
import com.manjesh.blog.security.JwtTokenHelper;
import com.manjesh.blog.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		 UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		String generateToken=this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(generateToken);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			throw new ApiException("Inavalid username or Password");
		}
		
	}
	@PostMapping("/register")
	public ResponseEntity<UserDto>regesterUser(@RequestBody UserDto userDto){
		UserDto newUser=this.userService.regesterNewUser(userDto);
		return new ResponseEntity<UserDto>(newUser,HttpStatus.CREATED);
	}

}
