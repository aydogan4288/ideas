package com.dojo.java_exam_2.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


import com.dojo.java_exam_2.models.User;
import com.dojo.java_exam_2.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
//	<<---------------Defining Constructor--------------->>
	
	public UserService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
//	<<---------------Create--------------->>
	
	public User createUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}

//	<<---------------Read--------------->>
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
//	<<---------------Update--------------->>
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
//	<<---------------Destroy--------------->>
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
//	<<---------------User Authentication--------------->>	
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			return false;
		}
		
		else {
			
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}
			
			else {
				return false;
			}
		}
	}

	
}
