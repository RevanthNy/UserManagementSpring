package com.userorgmanagement.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userorgmanagement.model.User;

@Repository
public interface RelationMappingRepository extends CrudRepository<User, Long> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO user_org_mapping VALUES (:orgId, :userId)", nativeQuery = true)
	public Integer insertRecord(@Param("userId")Long userId, @Param("orgId")Long OrgId);

	@Transactional
	@Query(value = "SELECT COUNT(*) FROM user_org_mapping WHERE org_id = :orgId and user_id = :userId", nativeQuery = true)
	public Integer findByCompositeKey(Long userId, Long orgId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM user_org_mapping WHERE org_id = :orgId and user_id = :userId", nativeQuery = true)
	public Integer deleteByCompositeKey(Long userId, Long orgId);

	@Transactional
	@Query(value = "SELECT user_id FROM user_org_mapping WHERE org_id = :orgId", nativeQuery = true)
	public ArrayList<Long> findAllUserByOrgId(Long orgId);
	
	@Transactional
	@Query(value = "SELECT org_id FROM user_org_mapping WHERE user_id = :userId", nativeQuery = true)
	public ArrayList<Long> findAllOrgsOfUser(Long userId);

}
