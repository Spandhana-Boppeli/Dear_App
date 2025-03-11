package com.google.dearapp.dto;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.dearapp.util.UserGender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatchingUser {
	private String name;
	private Integer age;
	private UserGender gender;
	private List<String> interset;
	private Integer ageDifference;
	private Integer matchingInterestCount;
	
	
	

}
