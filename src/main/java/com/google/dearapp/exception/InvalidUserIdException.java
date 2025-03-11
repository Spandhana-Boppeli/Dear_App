package com.google.dearapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InvalidUserIdException extends RuntimeException {
	
	private String message;
	
	@Override
	public String getMessage() {
		return this.message;
	}
	

}
