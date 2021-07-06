package com.nurturecommunity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//import jdk.internal.org.jline.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.FoodList;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.constant.Queries;
import com.nurturecommunity.repository.AddFoodDetailsRepository;
import com.nurturecommunity.repository.UserRepository;
import com.nurturecommunity.services.GetRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8060", allowedHeaders = "*",allowCredentials="true")
public class MainController {

	@Autowired
	private GetRequest getRequest;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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


	/*
	 * @PostMapping("/users/register") public ResponseEntity
	 * registerUser(@Valid @RequestBody AppUser newUser) { List<AppUser> users =
	 * userRepository.findAll(); System.out.println("New user: " +
	 * newUser.toString()); for (AppUser user : users) {
	 * System.out.println("Registered user: " + newUser.toString()); if
	 * (user.equals(newUser)) { //System.out.println("UserAlreadyExists"); return
	 * ResponseEntity.ok("UserAlreadyExists"); } } AppUser user =
	 * userRepository.save(newUser);
	 * 
	 * if(user!=null && user.getId() !=null) return ResponseEntity.ok("Success"); r
	 *//*
		 * eturn ResponseEntity.ok("Failure"); }
		 */
	
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
	
	
	@PostMapping(value = "/users/register", consumes = "multipart/form-data")
	public ResponseEntity<String> createNewObjectWithImage(@RequestParam("model") String myParams,
			@RequestParam(value = "cover_image", required = false) MultipartFile multipartfile) {
		
		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject)jsonParser.parse(myParams);
		AppUser user = new AppUser();
		
		
		 user.setFirst_name(object.get("first_name").toString());
		 user.setLast_name(object.get("last_name").toString());
		 user.setAddress(object.get("address").toString());
		 user.setEmailaddress(object.get("emailaddress").toString());
		 user.setPassword(object.get("password").toString());
		 user.setRestaurant_name(object.get("restaurant_name").toString());
		 user.setLicense_number(object.get("license_number").toString());
		 user.setOpens_at(object.get("opens_at").toString());
		 user.setCloses_at(object.get("closes_at").toString());
		 user.setPhone(object.get("phone").toString());
		 user.setZip(object.get("zip").toString());
		 user.setUser_type(object.get("user_type").toString());
		 
		 List<AppUser> users = userRepository.findByEmailaddress(user.getEmailaddress());
		 
		 if(!users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);//409
		 }else {
			 AppUser newuser = userRepository.save(user);
			 savedata(newuser,multipartfile);
			 return new ResponseEntity<>(HttpStatus.OK);
		 }
		 
	}
	


	private void savedata(AppUser newuser, MultipartFile multipartfile) {
		
		try {
			jdbcTemplate.update(Queries.AddMultipartFiles, newuser.getId(),multipartfile.getName(), multipartfile.getBytes());
			
		} catch (DataAccessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
    
		@PostMapping(value = "/addFoodDetails",consumes = "multipart/form-data")
		@ResponseStatus(HttpStatus.CREATED)
		public void AddFood(@RequestParam("model") String myParams,
				@RequestParam(value = "uploadedImage", required = false) MultipartFile multipartfile, HttpServletRequest request) {
			
			
			String emailaddress=getCookies(request);
			
			List<AppUser> obj=userRepository.findByEmailaddress(emailaddress);
			//System.out.println(obj);
			
			//addFoodDetailsRepository.save(new AddFoodDetails(obj.get(0).getId(),obj.get(0).getRestaurant_name(),addFood.getItemName(),addFood.getItemDescription(),addFood.getUploadedPicture(),addFood.getNumberofPackets(),addFood.getLocationChange(),addFood.getPickupTime(), addFood.getAddress()));
			
			
			
		}
	
	
}

