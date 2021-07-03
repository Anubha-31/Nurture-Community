package com.nurturecommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nurturecommunity.Dao.Food;
import com.nurturecommunity.repository.FoodRepository;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.services.GetRequest;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:8060")
public class MainController {

	@Autowired
	private GetRequest getRequest;
	
	@Autowired
	private com.nurturecommunity.model.LoginDetails Login;
	
	@Autowired
	FoodRepository foodRepository;

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

	
	@GetMapping("/login")
	public List<User> getRequest() throws Exception {
		return this.getRequest.getLoginresponse();
	}
	// testing
	@GetMapping("/foodlist")
	public List<Food> getRequestFoodList() throws Exception {
		return this.getRequest.getFoodList();
	}
	
	
	@PostMapping("/add-Food")
	public String addfood(@Valid @RequestBody Food food)
	{
		
		foodRepository.save(food);
		
		return "Food Added Sucessfully";
	}
}

