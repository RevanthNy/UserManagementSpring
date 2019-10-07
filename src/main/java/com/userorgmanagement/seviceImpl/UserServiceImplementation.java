package com.userorgmanagement.seviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userorgmanagement.dto.UserDTO;
import com.userorgmanagement.model.Organization;
import com.userorgmanagement.model.User;
import com.userorgmanagement.repository.OrganizationRepository;
import com.userorgmanagement.repository.RelationMappingRepository;
import com.userorgmanagement.repository.UserRepository;
import com.userorgmanagement.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RelationMappingRepository relationMappingRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public User createUser(UserDTO userDTO) throws Exception {
		User user = new User();
		try {
			BeanUtils.copyProperties(userDTO, user);
			userRepository.save(user);
		} catch(Exception e) {
			log.error("exception in creating a user", e.getMessage());
		}
		return user;
	}

	@Override
	public ArrayList<Organization> getAllOrgsFromUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		ArrayList<Organization> resultOrgs = new ArrayList<>();
		if(user.isPresent()) {
			//get all users of an organization from the mapping table
			ArrayList<Long> orgIds = relationMappingRepository.findAllOrgsOfUser(userId);
			if(!orgIds.isEmpty()) {
				for(Long orgId: orgIds) {
					Optional<Organization> organization = organizationRepository.findById(orgId);
					resultOrgs.add(organization.get());
				}
				log.info("returning all the Organizations of User with id "+userId+"");
			}
			else
				log.error("failed to find the organizations for user with id "+userId+"");
		}
		return resultOrgs;
	}

}
