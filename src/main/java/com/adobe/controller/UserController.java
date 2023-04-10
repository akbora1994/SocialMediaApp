package com.adobe.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.exceptions.LoginException;
import com.adobe.exceptions.UserException;
import com.adobe.model.Login;
import com.adobe.model.User;
import com.adobe.service.UserService;
import com.adobe.service.UserSessionService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSessionService usService;
	
	@PostMapping("/users")
	public ResponseEntity<User> createNewUser(@RequestBody User user) throws LoginException{
		
		User usr = userService.createNewUser(user);
		return new ResponseEntity<User>(usr,HttpStatus.OK);
	}

	@PostMapping("/users/login")
	public ResponseEntity<String> loginAccount(@RequestBody Login login) throws UserException, LoginException {
		
		String str =  usService.logIntoAccount(login);
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/logout/{id}")
	public ResponseEntity<String> logoutAccount(@PathVariable("id") String uniqueId) throws LoginException{
		
		String message = usService.logoutFromAccount(uniqueId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserByIdHandler(@PathVariable ("id") Integer id) throws UserException{
		return new ResponseEntity<User>(userService.getUserById(id),HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserByIdHandler(@RequestBody User user, @PathVariable ("id") Integer id) throws UserException{
		return new ResponseEntity<User>(userService.updateUserById(id, user), HttpStatus.OK);
	}
	
	@CrossOrigin
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserByIdHandler(@PathVariable ("id") Integer id) throws UserException{
		return new ResponseEntity<String>(userService.deleteUserById(id),HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/analytics/users")
	public ResponseEntity<List<User>> getAllUserHandler() throws UserException{
		return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	
}
