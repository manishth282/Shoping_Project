package org.jsp.api.dao;

import java.util.Optional;

import org.jsp.api.dto.User;
import org.jsp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public User updateUser(User user) {
		return repository.save(user);
	}
	public User verifyUser(String token) {
		return repository.findUserByToken(token);
	}
	public User findUserByEmail(String email) {
		return repository.findUserByEmail(email);
	}
	public Optional<User> loginVerifyByUser(String email,String password){
		return repository.loginVerifyByUser(email, password);
	}
	public Optional<User> findById(int user_id){
		return repository.findById(user_id);
	}
}
