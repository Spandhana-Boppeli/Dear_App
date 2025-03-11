package com.google.dearapp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.InvalidUrlException;

import com.google.dearapp.exception.DuplicateEmailException;
import com.google.dearapp.exception.DuplicatePhoneException;
import com.google.dearapp.exception.InvalidUserIdException;
import com.google.dearapp.exception.NoActiveUsersFoundException;
import com.google.dearapp.exception.NoBlockedUsersFoundException;
import com.google.dearapp.exception.NoInActiveUsersFoundException;
import com.google.dearapp.structure.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseStructure<String> duplicateEmailExceptionHandler(DuplicateEmailException e){
		ResponseStructure<String> rs= new ResponseStructure<>();
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Account Already Exist For This Email");
		rs.setBody(e.getMessage());
		return rs;
		
	}
	
	@ExceptionHandler(DuplicatePhoneException.class)
		public ResponseStructure<String> duplicatephoneExceptionHandler(DuplicatePhoneException e){
			ResponseStructure<String> rs= new ResponseStructure<>();
			rs.setStatus(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Account Already Exist For This mobile number");
			rs.setBody(e.getMessage());
			return rs;
		}
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseStructure<String> InvalidUserIdExceptionHandler(InvalidUserIdException e){
		ResponseStructure<String> rs= new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Invalid User Id ");
		rs.setBody(e.getMessage());
		return rs;
	}
	
	@ExceptionHandler(NoActiveUsersFoundException.class)
	
	public ResponseStructure<String> noActiveUsersFoundExceptionHandler(NoActiveUsersFoundException e){
		ResponseStructure<String> rs= new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("No Active Users Found ");
		rs.setBody(e.getMessage());
		return rs;
	}
	@ExceptionHandler(NoInActiveUsersFoundException.class)
	public ResponseStructure<String> noInActiveUsersFoundExceptionHandler(NoInActiveUsersFoundException e){
		ResponseStructure<String> rs= new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("No In_Active Users Found ");
		rs.setBody(e.getMessage());
		return rs;
	}
	
	@ExceptionHandler(NoBlockedUsersFoundException.class)
	public ResponseStructure<String> noBlockedUsersFoundExceptionHandler(NoBlockedUsersFoundException e){
		ResponseStructure<String> rs= new ResponseStructure<>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage("No Blocked Users Found ");
		rs.setBody(e.getMessage());
		return rs;
	}
	
	
	
	
	
	
		
	}


