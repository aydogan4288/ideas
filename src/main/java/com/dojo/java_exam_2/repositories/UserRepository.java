package com.dojo.java_exam_2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dojo.java_exam_2.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findAll();
	
	public User findByEmail(String email);

}
