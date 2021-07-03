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
import org.springframework.web.bind.annotation.RestController;

import com.nurturecommunity.Dao.AddFoodDetails;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.FoodList;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.repository.AddFoodDetailsRepository;
import com.nurturecommunity.repository.UserRepository;
import com.nurturecommunity.services.GetRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8060")
public class MainController {

	@Autowired
	private GetRequest getRequest;
	
	@Autowired
	AddFoodDetailsRepository addFoodDetailsRepository;
	
	@Autowired
	private com.nurturecommunity.model.LoginDetails Login;
	
	@GetMapping("/login")
	public List<User> getRequest() throws Exception {
		return this.getRequest.getLoginresponse();
	}
	
	@GetMapping("/ListofRestuarants")
	public List<FoodList> getFoodList() throws Exception {
		return this.getRequest.getFoodresponse();
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
	
	
	

	
	
	
//	@GetMapping("/ListOfRestaurants")
//	public ResponseEntity<List<AddFoodDetails>> getAllTutorials(@RequestParam(required = false) String restaurantName) {
//		try {
//			List<AddFoodDetails> obj = new ArrayList<AddFoodDetails>();
//
//			if (restaurantName == null)
//				addFoodDetailsRepository.findAll().forEach(obj::add);
//			else
//				addFoodDetailsRepository.findByRestaurantNameContaining(restaurantName).forEach(obj::add);
//
//			if (obj.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(obj, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}	
//	}
	
    
	@PostMapping("/addFoodDetails")
	public ResponseEntity<AddFoodDetails> createTutorial(@RequestBody AddFoodDetails addFood) {
		try {
			AddFoodDetails _addFood = addFoodDetailsRepository
					.save(new AddFoodDetails(addFood.getRestaurantName(), addFood.getItemName(), addFood.getItemDescription(), addFood.getUploadedPicture(), addFood.getNumberofPackets(), addFood.getLocationChange(), addFood.getPickupTime()));
			return new ResponseEntity<>(_addFood, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}

