package org.jsp.api.controller;

import org.jsp.api.dto.ResponseStructure;
import org.jsp.api.dto.User;
import org.jsp.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user,HttpServletRequest request){
		return service.saveUser(user, request);
	}
	@GetMapping("/users/verify")
	public ResponseEntity<ResponseStructure<String>> verifyUser(@RequestParam String token) {
		return service.verifyUser(token);
	}
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	@GetMapping("users/forget-password")
	public ResponseEntity<ResponseStructure<String>> forgotPassword(@RequestParam String email,HttpServletRequest request){
		return service.sendResetPasswordLink(email, request);
	}
	@PostMapping("/users/verify-login")
	public ResponseEntity<ResponseStructure<User>> loginVerifyByUser(@RequestParam String email,@RequestParam String password){
		return service.loginVerifyByUser(email, password);
	}
}
