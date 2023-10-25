package org.jsp.api.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int home_number;
	private String building_name;
	private String area;
	private String street_name;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;
}
