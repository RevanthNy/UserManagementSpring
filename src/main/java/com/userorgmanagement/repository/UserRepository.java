package com.userorgmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userorgmanagement.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findById(@Param("id")Integer userId);

}
