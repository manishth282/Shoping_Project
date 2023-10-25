package org.jsp.api.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
//**@Data user for automatic getter and setter use for this use lombok
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(nullable = false, unique = true)
	private String email;
	private String token;
	private String status;
	@Column(nullable = false)
	private String password;
	@Column (nullable = false)
	private String gender;
	@OneToMany
	@JoinTable(name="user_cart",joinColumns=@JoinColumn(name = "user_id"),inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<Product> cart;
	@OneToMany
	@JoinTable(name="user_wishList",joinColumns=@JoinColumn(name = "user_id"),inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<Product> wishList;
}
