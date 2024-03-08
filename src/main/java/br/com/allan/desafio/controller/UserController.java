package br.com.allan.desafio.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allan.desafio.domain.user.User;
import br.com.allan.desafio.domain.user.UserDto;
import br.com.allan.desafio.domain.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@PostMapping
	@Transactional
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<User> createUser(@RequestBody UserDto user){
		User newUser = service.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<List<User>> getAllUsers(){
		var users = this.service.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);

	}
}
