package org.jsp.api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jsp.api.dao.MerchantDao;
import org.jsp.api.dto.EmailConfiguration;
import org.jsp.api.dto.Merchant;
import org.jsp.api.dto.ResponseStructure;
import org.jsp.api.exception.IdNotFoundException;
import org.jsp.api.exception.InvalidCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	@Autowired
	private EmailConfiguration configuration;
	@Autowired
	private ShoppingCartMailService mailService;
	@Autowired
	private GenerateLinkService service; 

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setData(dao.saveMerchant(merchant));
		structure.setMessage("Merchant Data Successfully saved");
		structure.setStatuscode(HttpStatus.CREATED.value());

		//Creating EmailConfiguration and passing to MailService to send the mail,
		//and by using GenerateLinkService to generate link to send with mail.
		configuration.setSubject("Registration Successful.");
		configuration.setText(
				"Hello Mr. " + merchant.getName() 
				+ "\nYou have successfully Registered as a Shoping cart Merchant"
				+ "\nPlease click on the link : " + service.getVerificationLink(request, merchant));
		Map<String, String> user = new HashMap<>();
		user.put("email", merchant.getEmail());
		user.put("name", merchant.getName());
		configuration.setUser(user);

		mailService.sendWelcomeMail(configuration);
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<String>> verifyMerchant(String token) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Merchant merchant = dao.verifyMerchant(token);
		if (merchant != null) {
			merchant.setToken(null);
			merchant.setStatus("Active");
			dao.updateMerchant(merchant);
			structure.setData("Data is verified");
			structure.setStatuscode(HttpStatus.OK.value());
			structure.setMessage("Your Account is Activated");
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("Data not verified");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Your Account is not Active.");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Merchant existingMerchant = findById(merchant.getId()).getBody().getData();
		
		existingMerchant.setName(merchant.getName());
		existingMerchant.setPhone(merchant.getPhone());
		existingMerchant.setPassword(merchant.getPassword());
		existingMerchant.setGst(merchant.getGst());
		
		structure.setData(dao.updateMerchant(existingMerchant)); //saveMerchant if id is same it will update.
		structure.setMessage("Account is updated.");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setData(dao.findById(id).get());
		structure.setMessage("Merchant data found.");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<String>> sendResetPasswordLink(String email, HttpServletRequest request) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Merchant merchant = dao.findMerchantByEmail(email);
		if(merchant != null) {
			Map<String, String> map = new HashMap<>();
			map.put("email",merchant.getEmail());
			map.put("name",merchant.getName());
			
			configuration.setSubject("Reset Password.");
			configuration.setText("Hello Mr. "+merchant.getName()
							+"\nYou have requested for password change please click on the following link: "
							+service.getResetPasswordLink(request,merchant));
			configuration.setUser(map);
			mailService.sendWelcomeMail(configuration);
			structure.setData("Reset password link sent to email.");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			structure.setMessage("merchant found");
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData("Please Register.");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("merchant not found");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> loginVerifyByMerchant(String email, String password) {
		Optional<Merchant> recMerchant = dao.loginVerifyByMerchant(email,password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("User login Successfully");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
}
