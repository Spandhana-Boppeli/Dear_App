package com.google.dearapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.dearapp.dao.UserDao;
import com.google.dearapp.dto.MatchingUser;
import com.google.dearapp.entity.User;
import com.google.dearapp.exception.DuplicateEmailException;
import com.google.dearapp.exception.DuplicatePhoneException;
import com.google.dearapp.exception.InvalidOTPException;
import com.google.dearapp.exception.InvalidUserIdException;
import com.google.dearapp.exception.NoActiveUsersFoundException;
import com.google.dearapp.exception.NoBlockedUsersFoundException;
import com.google.dearapp.exception.NoInActiveUsersFoundException;
import com.google.dearapp.structure.ResponseStructure;
import com.google.dearapp.util.EmailService;
import com.google.dearapp.util.SortByAgeDifferenceAsc;
import com.google.dearapp.util.UserGender;
import com.google.dearapp.util.UserStatus;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private EmailService emailservice;

	public ResponseStructure<User> saveUser(User user) {
		
		Optional<User> optional1 =userdao.findByEmail(user.getEmail());
		if(optional1.isPresent()) {
			throw new DuplicateEmailException("Account Already Exist with this Email "+user.getEmail()+" Please Try With New Email");
		}
		
		Optional<User> optional2=userdao.findByPhone(user.getPhone());
		if(optional2.isPresent()) {
			throw new DuplicatePhoneException("Account Already Exist with this Phone Number"+user.getPhone() +"Please Try With New Phone Number");
		}
		//send email
	
		user.setStatus(UserStatus.IN_ACTIVE);
		int otp=emailservice.getOTP();
		user.setOtp(otp);
		user=userdao.saveUser(user);
		emailservice.sendFirstEmail(user);
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("User Saved Successfully");
		rs.setBody(user);
		return rs;
	}

	public ResponseStructure<List<User>> findAllUsers() {
		
		List<User> users=userdao.findAllUsers();
		ResponseStructure<List<User>> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("All User Found Successfully");
		rs.setBody(users);
		return rs;
	}

	public ResponseStructure<User> findUserById(Long id) {
		Optional<User> optional=userdao.findUserById(id);
		if(optional.isEmpty()) {
			throw new InvalidUserIdException("Invalid User Id : "+id+" Unable to Find User");
		}
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage(" User Found By Id Successfully");
		rs.setBody(optional.get());
		return rs;
	}

	public ResponseStructure<List<User>> findAllMaleUsers() {
		
		List<User> maleusers=userdao.findByGender(UserGender.MALE);
		ResponseStructure<List<User>> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage(" All Male Users Found Successfully");
		rs.setBody(maleusers);
		return rs;

	}

	public ResponseStructure<List<User>> findAllFemaleUsers() {
		List<User> femaleusers=userdao.findByGender(UserGender.FEMALE);
		ResponseStructure<List<User>> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage(" All Female Users Found Successfully");
		rs.setBody(femaleusers);
		return rs;
		
	}

//	public ResponseStructure<List<User>> findByStatus(UserStatus status ) {
//		ResponseStructure<List<User>> rs=new ResponseStructure<>();
//		
//		List<User> activeusers=userdao.findByStatus(status);
//		if(activeusers.isEmpty()) {
//			throw new NoActiveUsersFoundException("No Active Users ");
//		}
//		else {
//			
//			rs.setStatus(HttpStatus.OK.value());
//			rs.setMessage(" All Active Users Found Successfully");
//			rs.setBody(activeusers);
//		}
//		 
//		return rs;
//	}

	public ResponseStructure<List<User>> findAllActiveStatus() {
		List<User> users=userdao.findUserByStatus(UserStatus.ACTIVE);
        ResponseStructure<List<User>> rs=new ResponseStructure<>();
		if(users.isEmpty()) {
			throw new NoActiveUsersFoundException("No Active Users ");
		}
		else {
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage(" All Active Users Found Successfully");
			rs.setBody(users);
		}
		return rs;
	}

	public ResponseStructure<List<User>> findAllInActiveStatus() {
		List<User> users=userdao.findUserByStatus(UserStatus.IN_ACTIVE);
        ResponseStructure<List<User>> rs=new ResponseStructure<>();
		if(users.isEmpty()) {
			throw new NoInActiveUsersFoundException("No In_Active Users ");
		}
		else {
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage(" All In_Active Users Found Successfully");
			rs.setBody(users);
		}
		 
		return rs;
	}

	public ResponseStructure<List<User>> findAllBlockedStatus() {
		List<User> users=userdao.findUserByStatus(UserStatus.BLOCKED);
        ResponseStructure<List<User>> rs=new ResponseStructure<>();
		if(users.isEmpty()) {
			throw new NoBlockedUsersFoundException("No Blocked Users ");
		}
		else {
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage(" All Blocked Users Found Successfully");
			rs.setBody(users);
		}
		return rs;
	}

	public ResponseStructure<List<User>> findAllDeletedStatus() {
		List<User> users=userdao.findUserByStatus(UserStatus.DELETED);
        ResponseStructure<List<User>> rs=new ResponseStructure<>();
		if(users.isEmpty()) {
//			throw new NoDeletedUsersFoundException("No Deleted Users ");
		}
			
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage(" All Deleted Users Found Successfully");
			rs.setBody(users);
		return rs;
	}

	public ResponseStructure<List<User>> findAllTerminatedStatus() {
		List<User> users=userdao.findUserByStatus(UserStatus.TERMINATED);
        ResponseStructure<List<User>> rs=new ResponseStructure<>();
//		if(users.isEmpty()) {
////			throw new NoTerminatedUsersFoundException("No Deleted Users ");
//		}
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage(" All Terminated Users Found Successfully");
			rs.setBody(users);

		return rs;
	}

	public ResponseStructure<User> SetStatusToInActive(Long id) {
		Optional<User> optional = userdao.findUserById(id);
		if(optional.isEmpty()) {
			throw new InvalidUserIdException("Invalid User Id : " +id+"Unable to set the Status to Inactive");
		}
		User u = optional.get();
		u.setStatus(UserStatus.IN_ACTIVE);
		u = userdao.saveUser(u);
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Status Upadted To In_ACTIVE");
		rs.setBody(u);
		return rs;
	}

	public ResponseStructure<User> SetStatusToBlocked(Long id) {
		Optional<User> optional = userdao.findUserById(id);
		if(optional.isEmpty()) 
			throw new InvalidUserIdException("Invalid User Id : " +id+"Unable to set the Status to Blocked");
		
		User u = optional.get();
		u.setStatus(UserStatus.BLOCKED);
		u = userdao.saveUser(u);
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Status Upadted To Blocked");
		rs.setBody(u);
		
		return rs;
	}

	public ResponseStructure<User> SetStatusToDeleted(Long id) {
		Optional<User> optional = userdao.findUserById(id);
		if(optional.isEmpty()) 
			throw new InvalidUserIdException("Invalid User Id : " +id+"Unable to set the Status to Deleted");
		
		User u = optional.get();
		u.setStatus(UserStatus.DELETED);
		u = userdao.saveUser(u);
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Status Upadted To Deleted");
		rs.setBody(u);
		
		return rs;
	}
	public ResponseStructure<User> SetStatusToTerminated(Long id) {
		Optional<User> optional = userdao.findUserById(id);
		if(optional.isEmpty()) 
			throw new InvalidUserIdException("Invalid User Id : " +id+"Unable to set the Status to Terminated");
		
		User u = optional.get();
		u.setStatus(UserStatus.TERMINATED);
		u = userdao.saveUser(u);
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Status Upadted To Terminated");
		rs.setBody(u);
		
		return rs;
	}

	public ResponseStructure<User> SetStatusToActive(Long id) {
		Optional<User> optional = userdao.findUserById(id);
		if(optional.isEmpty()) 
			throw new InvalidUserIdException("Invalid User Id : " +id+"Unable to set the Status to Active");
		User u = optional.get();
		u.setStatus(UserStatus.ACTIVE);
		u = userdao.saveUser(u);
		ResponseStructure<User> rs=new ResponseStructure<>();
		rs.setStatus(HttpStatus.OK.value());
		rs.setMessage("Status Upadted To Active");
		rs.setBody(u);
		return rs;
	}

	public ResponseStructure<List<User>> findAllActiveMaleUsers() {
		List<User> users=userdao.findByGenderAndStatus(UserGender.MALE,UserStatus.ACTIVE);
//		ResponseStructure<List<User>> rs=new ResponseStructure<>();
//		rs.setStatus(HttpStatus.OK.value());
//		rs.setMessage("All ACtive Male Users Found");
//		rs.setBody(users);
		return new ResponseStructure<List<User>>(HttpStatus.OK.value(), "All Active Male Users Found", users);
	}

	public ResponseStructure<List<User>> findAllActiveFemaleUsers() {
		List<User> users=userdao.findByGenderAndStatus(UserGender.FEMALE,UserStatus.ACTIVE);
		return new ResponseStructure<List<User>>(HttpStatus.OK.value(), "All Active Female Users Found", users);
	}
	public ResponseStructure<List<MatchingUser>> findAllMatches(Long id, Integer top) {
		 Optional<User> optional = userdao.findUserById(id);
		 if(optional.isEmpty()) throw new InvalidUserIdException("Invalid User id : "+id+", Unable To Find The Best Matches");
		User user = optional.get();
		UserGender gender = user.getGender();
//		System.out.println(gender.equals(UserGender.MALE)?UserGender.FEMALE:UserGender.MALE);
		 List<User> users=new ArrayList<>();
		 if(gender.equals(UserGender.MALE)) {
			 users=userdao.findByGender(UserGender.FEMALE);
		 }
		 else
			 users=userdao.findByGender(UserGender.MALE);
//		 printCollection(users);
		 List<MatchingUser> matchingUsers=new ArrayList<>();
		 for(User u:users) {
			 MatchingUser mu=new MatchingUser(u.getName(), u.getAge(), u.getGender(), u.getInterests(), Math.abs(user.getAge()-u.getAge()), countInterset(user.getInterests(),u.getInterests())); 
//			 mu.setName(u.getName());
//			 mu.setAge(u.getAge());
//			 mu.setInterset(u.getInterests());
//			 mu.setAgeDifference(Math.abs(user.getAge()-u.getAge()));
//			 mu.setMatchingInterestCount(countInterset(user.getInterests(),u.getInterests()));
//			 mu.setGender(u.getGender());
			 matchingUsers.add(mu); 
		 }
//		 printCollection(matchingUsers);
		 Collections.sort(matchingUsers,new SortByAgeDifferenceAsc());
//		 printCollection(matchingUsers);
		 matchingUsers=matchingUsers.stream().limit(top).collect(Collectors.toList());
		 printCollection(matchingUsers);
		 return new ResponseStructure<List<MatchingUser>>(HttpStatus.OK.value(), "All Matching Users Found", matchingUsers);
		 }
	private  int countInterset(List<String> list1,List<String> list2) {
		int c=0;
		for(String s:list1) {
			if(list2.contains(s))
				c++;
		}
		return c;
	}
	private void printCollection(Collection c) {
	      for(Object o:c) {
	    	  System.out.println(o);
	      }	
	}

	public ResponseStructure<User> verifyOTP(Long id, int otp) {
	 Optional<User> optional =userdao.findUserById(id);
	 if(optional.isEmpty()) {
		 throw new InvalidUserIdException("Invalid user id : "+id+"Unable to verify user OTP :"+otp);
		 
	 }
	 User user = optional.get();
	 if(otp !=user.getOtp()) {
		 throw new InvalidOTPException("Invalid OTP :"+otp+"Unable To Verify User");
	 }
	 user.setStatus(UserStatus.ACTIVE);
	 user=userdao.saveUser(user);
	 ResponseStructure<User> rs=new ResponseStructure<>();
	 rs.setStatus(HttpStatus.OK.value());
	 rs.setMessage("User Found Successfully");
	 rs.setBody(user);
		return rs;
	}
	
	
}
