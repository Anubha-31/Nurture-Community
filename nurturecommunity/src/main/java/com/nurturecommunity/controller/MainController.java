package com.nurturecommunity.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//import jdk.internal.org.jline.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nurturecommunity.Dao.AddFoodDetails;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.FoodList;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.repository.AddFoodDetailsRepository;
import com.nurturecommunity.repository.UserRepository;
import com.nurturecommunity.services.GetRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
				//System.out.println("UserAlreadyExists");
				return ResponseEntity.ok("UserAlreadyExists");
			}
		}
		AppUser user = userRepository.save(newUser);
		
		if(user!=null && user.getId() !=null)
			return ResponseEntity.ok("Success");
		return ResponseEntity.ok("Failure");
	}
	
	@PostMapping("/users/login")
	synchronized public ResponseEntity loginUser(@Valid @RequestBody AppUser user, HttpServletResponse response) {
		List<AppUser> users = userRepository.findAll();

		  String Usertype="Failure";
		for (AppUser other : users) {
			if (other.getEmailaddress().equalsIgnoreCase(user.getEmailaddress()) &&
					other.getPassword().equals(user.getPassword())) {
				user.setLoggedin(true);
				// userRepository.save(user);
				Cookie cookie = new Cookie("EmailId", user.getEmailaddress());
				cookie.setMaxAge(7 * 24 * 60 * 60);
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
				cookie.setPath("/");
				response.addCookie(cookie);
				Usertype=other.getUser_type();
				//System.out.println("Cookies");
				return ResponseEntity.ok(Usertype);

			}
		}
		return ResponseEntity.ok(Usertype);
	}
	
//	@PostMapping(value = "/upload", consumes = "multipart/form-data")
//	public String upload(@RequestParam("cover_image") MultipartFile multipartfile) {
//		return String.format("Success %s", multipartfile.getOriginalFilename());
//	}
	
//	@PostMapping(value = "/upload", consumes = "multipart/form-data")
//	public String createNewObjectWithImage(@RequestParam("model") String model, @RequestParam(value = "cover_image", required = false) MultipartFile multipartfile) {
//		return String.format("Success %s", multipartfile.getOriginalFilename());
//	}
	
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public String createNewObjectWithImage(@RequestParam("model") List<String> myParams, @RequestParam(value = "cover_image", required = false) MultipartFile multipartfile) {
		for(int i = 0; i < myParams.size(); i++) {
            System.out.println(myParams.get(i));
        }
		System.out.println(myParams.get(4));
		return String.format("Success %s", multipartfile.getOriginalFilename());
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
	
		String getCookies(HttpServletRequest request) {
		String emailid = null;
		Cookie[] cookies = request.getCookies();

		 for (Cookie cookie : cookies) {
		if (cookie.getName().equals("EmailId")) {
		emailid = cookie.getValue();
		}
		}
		System.out.println(request.getCookies());
		return emailid;
		}
	
    
		@PostMapping("/addFoodDetails")
		@ResponseStatus(HttpStatus.CREATED)
		public void AddFood(@RequestBody AddFoodDetails addFood,HttpServletRequest request) {
			
			
			String emailaddress=getCookies(request);
			
			List<AppUser> obj=userRepository.findByEmailaddress(emailaddress);
			//System.out.println(obj);
			
			addFoodDetailsRepository.save(new AddFoodDetails(obj.get(0).getId(),obj.get(0).getRestaurant_name(),addFood.getItemName(),addFood.getItemDescription(),addFood.getUploadedPicture(),addFood.getNumberofPackets(),addFood.getLocationChange(),addFood.getPickupTime(), addFood.getAddress()));
			
			
			
		}
	
	
}

