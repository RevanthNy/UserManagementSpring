package com.userorgmanagement.service;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;

import com.userorgmanagement.dto.UserDTO;
import com.userorgmanagement.model.Organization;
import com.userorgmanagement.model.User;

public interface UserService {

	@Bean
	public User createUser(UserDTO userDTO) throws Exception;

	@Bean
	ArrayList<Organization> getAllOrgsFromUser(Long userId);

}
