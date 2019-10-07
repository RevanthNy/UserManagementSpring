package com.userorgmanagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userorgmanagement.dto.UserDTO;
import com.userorgmanagement.model.Organization;
import com.userorgmanagement.model.User;
import com.userorgmanagement.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/createUser")
	@ApiOperation(value = "This API creates User by taking the User model/DTO as input")
	public  ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws Exception {
		User resUser = userService.createUser(userDTO);
		return new ResponseEntity<User>(resUser, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllOrganizations/{userId}")
	@ApiOperation(value = "This API is used to get all Organizations of a User based on userId")
	public ResponseEntity<ArrayList<Organization>> getAllOrgsFromUser(@PathVariable Long userId) throws Exception {
		ArrayList<Organization> result = userService.getAllOrgsFromUser(userId);
		return new ResponseEntity<ArrayList<Organization>>(result,HttpStatus.OK);
	}

}
