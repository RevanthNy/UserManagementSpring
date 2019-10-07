package com.userorgmanagement.seviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userorgmanagement.dto.OrganizationDTO;
import com.userorgmanagement.model.Organization;
import com.userorgmanagement.model.User;
import com.userorgmanagement.repository.OrganizationRepository;
import com.userorgmanagement.repository.RelationMappingRepository;
import com.userorgmanagement.repository.UserRepository;
import com.userorgmanagement.service.OrganizationService;

@Service
public class OrganizationServiceImplementation implements OrganizationService{

	private final static Logger log = LoggerFactory.getLogger(OrganizationServiceImplementation.class);

	@Autowired
	OrganizationRepository orgRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RelationMappingRepository relationMappingRepository;

	@Override
	public Organization createOrganization(OrganizationDTO organizationDTO) throws Exception {
		Organization organization = new Organization();
		try {
			BeanUtils.copyProperties(organizationDTO, organization);
			orgRepository.save(organization);
		} catch(Exception e) {
			log.error("exception in creating an organization", e.getMessage());
		}
		return organization;
	}

	@Override
	public Integer addUserToOrganization(Long userId, Long orgId) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Organization> organization=orgRepository.findById(orgId);
		Integer result = 0;
		Integer duplicateValidity = 0;
		if(user.isPresent() && organization.isPresent()) {
			//Update the mapping table
			duplicateValidity = relationMappingRepository.findByCompositeKey(userId, orgId);
			if(duplicateValidity == 1) {
				log.warn("duplicate record already exists with the user_id "+userId+" and org_id "+orgId);
				result = 10000;
			} else if(duplicateValidity == 0) {
				result = relationMappingRepository.insertRecord(userId, orgId);
			}
		}
		return result;
	}

	@Override
	public Integer deleteUserFromOrganization(Long userId, Long orgId) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Organization> organization=orgRepository.findById(orgId);
		Integer result = 0;
		if(user.isPresent() && organization.isPresent()) {
			//Delete from the mapping table
			result = relationMappingRepository.deleteByCompositeKey(userId, orgId);
			log.info("delete record with the user_id "+userId+" and org_id "+orgId);
		} else  {
			log.warn("no record with the user_id "+userId+" and org_id "+orgId);
		}
		return result;
	}

	@Override
	public ArrayList<User> getAllUsersFromOrganization(Long orgId) {
		Optional<Organization> organization=orgRepository.findById(orgId);
		ArrayList<User> resultUsers = new ArrayList<>();
		if(organization.isPresent()) {
			//get all users of an organization from the mapping table
			ArrayList<Long> userIds = relationMappingRepository.findAllUserByOrgId(orgId);
			System.out.println(userIds);
			if(!userIds.isEmpty()) {
				for(Long userId: userIds) {
					Optional<User> user = userRepository.findById(userId);
					resultUsers.add(user.get());
				}
				log.info("returning all the user in the organization with Id "+orgId+"");
			}
			else
				log.error("failed to find the users in the organization with id "+orgId+"");
		}
		return resultUsers;
	}

}
