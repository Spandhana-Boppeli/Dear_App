package com.google.dearapp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NoBlockedUsersFoundException extends RuntimeException {
	
	private String message;
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

}
