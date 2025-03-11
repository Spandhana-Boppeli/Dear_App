package com.google.dearapp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class DuplicatePhoneException extends RuntimeException {
	
	private String message;
	
	@Override
	public String getMessage() {
		
		return this.message;
	}

}
