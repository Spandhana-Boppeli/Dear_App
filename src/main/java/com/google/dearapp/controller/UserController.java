package com.google.dearapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.dearapp.dto.MatchingUser;
import com.google.dearapp.entity.User;
import com.google.dearapp.service.UserService;
import com.google.dearapp.structure.ResponseStructure;
import com.google.dearapp.util.UserStatus;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseStructure<User> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	@GetMapping
	public ResponseStructure<List<User>> finAllUsers(){
		return service.findAllUsers();
		
	}
		
	@GetMapping("/id/{id}")
	public ResponseStructure<User> findUserById(@PathVariable Long id){
		return service.findUserById(id);
    }
	
	@GetMapping("/gender/male")
	public ResponseStructure<List<User>> findAllMaleUsers(){
	return service.findAllMaleUsers();
	}
	
	@GetMapping("/gender/female")
	public ResponseStructure<List<User>> findAllFemaleUsers(){
		return service.findAllFemaleUsers();
		}
	
	@GetMapping("/status/active")
	public ResponseStructure<List<User>> findAllActiveStatus(){
		return service.findAllActiveStatus();
	}
	
	@GetMapping("/status/inactive")
	public ResponseStructure<List<User>> findAllInActiveStatus(){
		return service.findAllInActiveStatus();
	}
	
	@GetMapping("/status/blocked")
	public ResponseStructure<List<User>> findAllBlockedStatus(){
		return service.findAllBlockedStatus();
	}
	
	@GetMapping("/status/deleted")
	public ResponseStructure<List<User>> findAllDeletedStatus(){
		return service.findAllDeletedStatus();
	}
	
	@GetMapping("/status/terminated")
	public ResponseStructure<List<User>> findAllTerminatedStatus(){
		return service.findAllTerminatedStatus();
	}
	
	@PatchMapping("/status/inactive/{id}")
	public ResponseStructure<User> SetStatusToInActive(@PathVariable (name="id")Long id){
		return service.SetStatusToInActive(id);
	}
	
	@PatchMapping("/status/blocked/{id}")
	public ResponseStructure<User> SetStatusToInBlocked(@PathVariable (name="id")Long id){
		return service.SetStatusToBlocked(id);
	}
	
	@PatchMapping("/status/deleted/{id}")
	public ResponseStructure<User> SetStatusToInDeleted(@PathVariable (name="id")Long id){
		return service.SetStatusToDeleted(id);
	}
	
	@PatchMapping("/status/terminated/{id}")
	public ResponseStructure<User> SetStatusToInTerminated(@PathVariable (name="id")Long id){
		return service.SetStatusToTerminated(id);
	}
	
	@PatchMapping("/status/active/{id}")
	public ResponseStructure<User> SetStatusToActive(@PathVariable (name="id")Long id){
		return service.SetStatusToActive(id);
	}
	
	@GetMapping("/active/male")
	public ResponseStructure<List<User>> findAllActiveMaleUsers(){
		return service.findAllActiveMaleUsers();
	}
	
	@GetMapping("/active/female")
	public ResponseStructure<List<User>> findAllActiveFemaleUsers(){
		return service.findAllActiveFemaleUsers();
	}
	
	@GetMapping("/matches/{id}/{top}")   //users/matches/2/3
	public ResponseStructure<List<MatchingUser>> findAllMAtches(@PathVariable(name="id") Long id,@PathVariable(name="top") Integer top){
		return service.findAllMatches(id,top);
	}
	
//	@GetMapping("/status/{status}")
//	public ResponseStructure<List<User>> findByStatus(@PathVariable UserStatus status ){
//	return service.findByStatus(status);
//	}
	
	

	
		
	
	
 

}
