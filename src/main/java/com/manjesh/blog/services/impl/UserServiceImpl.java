package com.manjesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manjesh.blog.exceptions.*;
import com.manjesh.blog.config.AppConstant;
import com.manjesh.blog.entities.Role;
import com.manjesh.blog.entities.User;
import com.manjesh.blog.payloads.UserDto;
import com.manjesh.blog.repositories.RoleRepo;
import com.manjesh.blog.repositories.UserRepo;
import com.manjesh.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
	private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveUser=this.userRepo.saveAndFlush(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateduser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updateduser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User>users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
	User user=	this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId ));
	this.userRepo.delete(user);
	
	}
	//this is update
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
		return user;
		}
    public UserDto userToDto(User user) {
    	UserDto userDto=this.modelMapper.map(user, UserDto.class);
    	return userDto;
    }

	@Override
	public UserDto regesterNewUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role=this.roleRepo.findById(AppConstant.Normal_USER).get();
		user.getRoles().add(role);
		User newUser=this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}
}
