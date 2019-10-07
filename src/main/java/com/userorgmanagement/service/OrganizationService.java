package com.userorgmanagement.service;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;

import com.userorgmanagement.dto.OrganizationDTO;
import com.userorgmanagement.model.Organization;
import com.userorgmanagement.model.User;

public interface OrganizationService {
	
	@Bean
	public Organization createOrganization(OrganizationDTO organizationDTO) throws Exception;

	@Bean
	public Integer addUserToOrganization(Long userId, Long orgId);

	@Bean
	public Integer deleteUserFromOrganization(Long userId, Long orgId);

	@Bean
	public ArrayList<User> getAllUsersFromOrganization(Long orgId);
	
}
