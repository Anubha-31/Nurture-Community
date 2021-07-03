package com.nurturecommunity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

//import jdk.internal.org.jline.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nurturecommunity.Dao.AddFoodDetails;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.repository.AddFoodDetailsRepository;
import com.nurturecommunity.repository.UserRepository;
import com.nurturecommunity.services.GetRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8060")
public class MainController {

	@Autowired
	private GetRequest getLoginRequest;
	
	@Autowired
	AddFoodDetailsRepository addFoodDetailsRepository;
	
	@Autowired
	private com.nurturecommunity.model.LoginDetails Login;
	
	@GetMapping("/login")
	public List<User> getRequest() throws Exception {
		return this.getLoginRequest.getLoginresponse();
	}

	@Autowired
	UserRepository userRepository;


	@PostMapping("/users/register")
	public ResponseEntity registerUser(@Valid @RequestBody AppUser newUser) {
		List<AppUser> users = userRepository.findAll();
		System.out.println("New user: " + newUser.toString());
		for (AppUser user : users) {
			System.out.println("Registered user: " + newUser.toString());
			if (user.equals(newUser)) {
				System.out.println("User Already exists!");
				return ResponseEntity.ok("User Already exists");
			}
		}
		userRepository.save(newUser);
		System.out.println("Success!");
		return ResponseEntity.ok("Registered Successfully");
	}
	@PostMapping("/users/login")
	public ResponseEntity loginUser(@Valid @RequestBody AppUser user) {
		List<AppUser> users = userRepository.findAll();
		for (AppUser other : users) {
			if (other.equals(user) ) {
				user.setLoggedin(true);
//				userRepository.save(user);
				System.out.println("Woohoo! Logged in");
				return ResponseEntity.ok("You are now logged in");

			}
		}
		return ResponseEntity.ok("Failure");
	}
	
	
	@GetMapping("/ListOfRestaurants")
	public List<AddFoodDetails> getAllTutorials() {
		
		
		
		return addFoodDetailsRepository.findAll();	
	}
	
    
	@PostMapping("/addFoodDetails")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTutorial(@RequestBody AddFoodDetails addFood) {
		
		
		addFoodDetailsRepository.save(addFood);
		
		
		
	}
	
	
	
}

