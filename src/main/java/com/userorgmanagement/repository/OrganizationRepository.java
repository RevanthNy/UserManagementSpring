package com.userorgmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userorgmanagement.model.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

	Optional<Organization> findById(@Param("id")Integer orgId);

}
