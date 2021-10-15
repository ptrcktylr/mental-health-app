package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService US;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User u) {
		
		if (u == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(u);
		}
		
		u = US.addUser(u);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
}
