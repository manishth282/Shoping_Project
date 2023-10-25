package org.jsp.api.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.jsp.api.dao.UserDao;
import org.jsp.api.dto.EmailConfiguration;
import org.jsp.api.dto.ResponseStructure;
import org.jsp.api.dto.User;
import org.jsp.api.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private EmailConfiguration configuration;
	@Autowired
	private GenerateLinkService service;
	@Autowired
	private ShoppingCartMailService mailService;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user, HttpServletRequest request){
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.saveUser(user));
		structure.setMessage("User Data Stored.");
		structure.setStatuscode(HttpStatus.CREATED.value());
		
		configuration.setSubject("Registration Successful.");
		configuration.setText("Hello Mr. " + user.getName() 
							+ "\nYou have successfully Registered as a Shoping cart as User"
							+ "\nPlease click on the link : "+ service.getVerificationLink(request, user));
		Map<String, String> map = new HashMap<>();
		map.put("email", user.getEmail());
		map.put("name", user.getName());
		configuration.setUser(map);
		
		mailService.sendWelcomeMail(configuration);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<String>> verifyUser(String token) {
		ResponseStructure<String> structure= new ResponseStructure<>();
		User user = dao.verifyUser(token);
		if (user != null) {
			user.setToken(null);
			user.setStatus("Active");
			dao.updateUser(user);
			structure.setData("Your Account is Activated.");
			structure.setMessage("User is Verified.");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		structure.setData("Your Account is not Activated.");
		structure.setMessage("User is not Verified.");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User user){
		ResponseStructure<User> structure = new ResponseStructure<>();
		User existingUser = findById(user.getId()).getBody().getData();
		if (existingUser == null) {
			structure.setData(null);
			structure.setMessage("Data not found.");
			structure.setStatuscode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
		}
		existingUser.setName(user.getName());
		existingUser.setPassword(user.getPassword());
		existingUser.setPhone(user.getPhone());
		existingUser.setGender(user.getGender());
		structure.setData(dao.saveUser(existingUser));
		structure.setMessage("User data is Updated");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.findById(id).get());
		structure.setMessage("Data is fetched.");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> sendResetPasswordLink(String email,HttpServletRequest request) {
		ResponseStructure<String> structure= new ResponseStructure<>();
		User user = dao.findUserByEmail(email);
		if (user != null) {
			HashMap<String,String> map=new LinkedHashMap<>();
			map.put("email", user.getEmail());
			map.put("name",user.getName());
			configuration.setSubject("Reset Password");
			configuration.setUser(map);
			configuration.setText("Hello "+user.getName()+" you have requested for password change"
					+ "please click link for change the password : "+service.getResetPasswordLink(request, user));
			mailService.sendWelcomeMail(configuration);
			
			structure.setData("Reset password link sent to email.");
			structure.setMessage("To user");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData("Please Register");
		structure.setMessage("User is not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<User>> loginVerifyByUser(String email, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.loginVerifyByUser(email, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User login Successfull");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException(); //change to proper exception.
	}
}
