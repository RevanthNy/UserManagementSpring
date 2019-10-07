package com.userorgmanagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userorgmanagement.dto.OrganizationDTO;
import com.userorgmanagement.model.Organization;
import com.userorgmanagement.model.User;
import com.userorgmanagement.service.OrganizationService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@PostMapping(value = "/createOrg")
	@ApiOperation(value = "This API creates an organization by taking the organization model/DTO as input")
	public ResponseEntity<Organization> createOrganization(@RequestBody OrganizationDTO organizationDTO) throws Exception {
		Organization org = organizationService.createOrganization(organizationDTO);
		return new ResponseEntity<Organization>(org, HttpStatus.CREATED);
	}

	@PostMapping(value = "/addUser/{orgId}")
	@ApiOperation(value = "This API is used to add User to an organization")
	public ResponseEntity<?> addUserToOrg(@RequestBody Long userId,@PathVariable Long orgId) throws Exception {
		Integer result = organizationService.addUserToOrganization(userId, orgId);
		if(result == 1) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else if(result == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@DeleteMapping(value = "/deleteUser/{orgId}")
	@ApiOperation(value = "This API is used to delete User from an organization")
	public ResponseEntity<?> deleteUserFromOrg(@RequestBody Long userId,@PathVariable Long orgId) throws Exception {
		Integer result = organizationService.deleteUserFromOrganization(userId, orgId);
		if(result == 1) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getAllUsers/{orgId}")
	@ApiOperation(value = "This API is used to get all Users from an organization based on organization Id")
	public ResponseEntity<ArrayList<User>> getAllUsersFromOrg(@PathVariable Long orgId) throws Exception {
		ArrayList<User> result = organizationService.getAllUsersFromOrganization(orgId);
		return new ResponseEntity<ArrayList<User>>(result,HttpStatus.OK);
	}
}
