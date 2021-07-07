package com.nurturecommunity.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.nurturecommunity.Dao.AddFoodDetails;
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
	synchronized public List<FoodList> getFoodList(HttpServletRequest request) throws Exception {
		String Cookie = getCookies(request);
		
		return this.getRequest.getFoodresponse(Cookie);
		
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
		List<AppUser> users = userRepository.findByEmailaddress(user.getEmailaddress());

		  String Usertype="Failure";
		for (AppUser other : users) {
			if (other.getEmailaddress().equalsIgnoreCase(user.getEmailaddress()) &&
					other.getPassword().equals(user.getPassword())) {
				Cookie cookie = new Cookie("EmailId", other.getEmailaddress());
				Cookie cookie1 = new Cookie("UserType", other.getUser_type());
				
				cookie.setMaxAge(7 * 24 * 60 * 60);
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
				cookie.setPath("/");
				cookie1.setMaxAge(7 * 24 * 60 * 60);
				cookie1.setSecure(true);
				cookie1.setHttpOnly(true);
				cookie1.setPath("/");
				response.addCookie(cookie);
				response.addCookie(cookie1);
				Usertype = other.getUser_type();
				return new ResponseEntity<>(Usertype,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	
	@PostMapping(value = "/users/register", consumes = "multipart/form-data")
	public ResponseEntity<String> createNewObjectWithImage(@RequestParam("model") String myParams,
			@RequestParam(value = "cover_image", required = false) MultipartFile multipartfile) {
		
		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject)jsonParser.parse(myParams);
		AppUser user = new AppUser();
		
		
		 user.setFirst_name(object.get("first_name").getAsString());
		 user.setLast_name(object.get("last_name").getAsString());
		 user.setAddress(object.get("address").getAsString());
		 user.setEmailaddress(object.get("emailaddress").getAsString());
		 user.setPassword(object.get("password").getAsString());
		 user.setRestaurant_name(object.get("restaurant_name").getAsString());
		 user.setLicense_number(object.get("license_number").getAsString());
		 user.setOpens_at(object.get("opens_at").getAsString());
		 user.setCloses_at(object.get("closes_at").getAsString());
		 user.setPhone(object.get("phone").getAsString());
		 user.setZip(object.get("zip").getAsString());
		 user.setUser_type(object.get("user_type").getAsString());
		 
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

	@GetMapping("/ListOfRestaurants")
	public ResponseEntity<List<AppUser>> getAllResturants(HttpServletRequest request) {
		try {
			List<AppUser> obj = new ArrayList<AppUser>();
			
			userRepository.findByUsertype("restaurant").forEach(obj::add);
			
			if (obj.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(obj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
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
	
    
		@PostMapping(value = "/addFoodDetails", consumes = "multipart/form-data")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity AddFood(@RequestParam("model") String myParams,
				@RequestParam(value = "uploadedImage", required = false) MultipartFile multipartfile,
				HttpServletRequest request) {

			String emailaddress = getCookies(request);
			
			AddFoodDetails foodDetails = new AddFoodDetails();
			List<AppUser> users = userRepository.findByEmailaddress(emailaddress);
			
			JsonParser jsonParser = new JsonParser();
			JsonObject object = (JsonObject)jsonParser.parse(myParams);
			
			
			foodDetails.setItemName(object.get("itemName").getAsString());
			foodDetails.setItemDescription(object.get("itemDescription").getAsString());
			foodDetails.setNumberofPackets(object.get("numberofPackets").getAsInt());
			foodDetails.setLocationChange(object.get("locationChange").getAsString());
			foodDetails.setAddress1(object.get("address1").getAsString());
			foodDetails.setAddress2(object.get("address2").getAsString());
			foodDetails.setCity(object.get("city").getAsString());
			foodDetails.setProvince(object.get("province").getAsString());
			foodDetails.setCountry(object.get("country").getAsString());
			foodDetails.setPickupTime(object.get("pickupTime").getAsString());
			foodDetails.setId(users.get(0).getId());
			foodDetails.setRestaurantName(users.get(0).getRestaurant_name());
			AddFoodDetails newfoodDetails = addFoodDetailsRepository.save(foodDetails);
					
			if(newfoodDetails != null && multipartfile !=null) {
				saveFooddata(newfoodDetails,multipartfile);				
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}

		private void saveFooddata(AddFoodDetails newfoodDetails, MultipartFile multipartfile) {
			try {
				jdbcTemplate.update(Queries.AddFoodFiles, newfoodDetails.getFoodDetailId(), multipartfile.getName(),
						multipartfile.getBytes());

			} catch (DataAccessException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
}

