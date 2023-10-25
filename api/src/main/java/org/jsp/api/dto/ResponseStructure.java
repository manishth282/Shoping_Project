package org.jsp.api.dto;

import lombok.Data;

@Data
public class ResponseStructure <T> {
	T data;
	String message;
	int statuscode;
}
