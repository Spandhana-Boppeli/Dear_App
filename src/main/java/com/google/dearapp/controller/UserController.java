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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@Operation(summary="To Save User",description="This API is used to Save User")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved All Users"),
			@ApiResponse(responseCode = "404",description = "Invalid Email  id"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
//	
	@PostMapping
	public ResponseStructure<User> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@Operation(summary="To Find User",description="This API is used to Find All the  Users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Found All Users"),
			@ApiResponse(responseCode = "404",description = "No Such Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	@GetMapping
	public ResponseStructure<List<User>> finAllUsers(){
		return service.findAllUsers();
		
	}
	
	@Operation(summary="To Save User By Id",description="This API is used to Save User By Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Id"),
			@ApiResponse(responseCode = "404",description = "Invalid User  id"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
		
	@GetMapping("/id/{id}")
	public ResponseStructure<User> findUserById(@PathVariable Long id){
		return service.findUserById(id);
    }
	
	@Operation(summary="To Find User By Gender",description="This API is used to Save User By Gender")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Gender"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch Male Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
		
	
	@GetMapping("/gender/male")
	public ResponseStructure<List<User>> findAllMaleUsers(){
	return service.findAllMaleUsers();
	}
	
	@Operation(summary="To Find User By Gender",description="This API is used to Save User By Gender")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Gender"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch Female Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	
	
	@GetMapping("/gender/female")
	public ResponseStructure<List<User>> findAllFemaleUsers(){
		return service.findAllFemaleUsers();
		}
	
	@Operation(summary="To Find User By Status",description="This API is used to Save User By Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Status"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch Active Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	@GetMapping("/status/active")
	public ResponseStructure<List<User>> findAllActiveStatus(){
		return service.findAllActiveStatus();
	}
	
	@Operation(summary="To Find User By Status",description="This API is used to Save User By Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Status"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch InActive Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	@GetMapping("/status/inactive")
	public ResponseStructure<List<User>> findAllInActiveStatus(){
		return service.findAllInActiveStatus();
	}
	
	@Operation(summary="To Find User By Status",description="This API is used to Save User By Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Status"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch Blocked Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	@GetMapping("/status/blocked")
	public ResponseStructure<List<User>> findAllBlockedStatus(){
		return service.findAllBlockedStatus();
	}
	
	@Operation(summary="To Find User By Status",description="This API is used to Save User By Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Status"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch Deleted Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	@GetMapping("/status/deleted")
	public ResponseStructure<List<User>> findAllDeletedStatus(){
		return service.findAllDeletedStatus();
	}
	
	@Operation(summary="To Find User By Status",description="This API is used to Save User By Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Saved  User By Status"),
			@ApiResponse(responseCode = "404",description = "Unable to Fetch Terminated Users"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	@GetMapping("/status/terminated")
	public ResponseStructure<List<User>> findAllTerminatedStatus(){
		return service.findAllTerminatedStatus();
	}
	
	@Operation(summary="To Update User Status By Id",description="This API is used to Update User  Status By Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Update  User By Status"),
			@ApiResponse(responseCode = "404",description = "Invalid User Id Unable to Update"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
	@PatchMapping("/status/inactive/{id}")
	public ResponseStructure<User> SetStatusToInActive(@PathVariable (name="id")Long id){
		return service.SetStatusToInActive(id);
	}
	
	@Operation(summary="To Update User Status By Id",description="This API is used to Update User  Status By Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Update  User By Status"),
			@ApiResponse(responseCode = "404",description = "Invalid User Id"),
			@ApiResponse(responseCode = "500",description = "Internal server")
			})
	
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
	
	
	@PatchMapping("/verifyotp/{id}/{otp}")
	public ResponseStructure<User> verifyOTP(@PathVariable (name="id") Long id,@PathVariable(name="otp") int otp){
		return service.verifyOTP(id,otp);
	}
	
	

	
		
	
	
 

}
