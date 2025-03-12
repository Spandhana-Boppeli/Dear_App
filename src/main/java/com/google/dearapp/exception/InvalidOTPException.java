package com.google.dearapp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InvalidOTPException extends RuntimeException {
   private String message;
   @Override
   public String getMessage() {
	   return this.message;
   }
}
