package com.google.dearapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.dearapp.entity.User;
import com.google.dearapp.repository.UserRepository;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserStatus;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository repo;

	public User saveUser(User user) {
		return repo.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public Optional<User> findByPhone(Long phone) {
		return repo.findByPhone(phone);
	}

	public List<User> findAllUsers() {
		return repo.findAll();
	}

	public Optional<User> findUserById(Long id) {
		return repo.findById(id);
	}

//	public List<User> findAllMaleUsers(UserGender gender) {
//		return repo.findByGender(gender);
//	}
//
//	public List<User> findAllFemaleUsers(UserGender gender) {
//		return repo.findByGender(gender);
//	}

//	public List<User> findByStatus(UserStatus status) {
//	     return repo.findByStatus(status);
//	}

	public List<User> findUserByStatus(UserStatus status) {
		return repo.findByStatus(status);
	}

	public List<User> findByGenderAndStatus(UserGender gender, UserStatus status) {
		
		return repo.findUserByGenderAndStatus(gender,status);
	}

	public List<User> findByGender(UserGender gender) {
		
		return repo.findByGender(gender);
	}

}
