package com.nurturecommunity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@CrossOrigin(origins = "http://locahost:8060/", allowedHeaders = "*",allowCredentials="true")
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
	
	
	
	@GetMapping("/Listoffooditems")
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
			if (other.getEmailaddress().equalsIgnoreCase(user.getEmailaddress())) {
				int strength = 10;
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);
				boolean isMatched = encoder.matches(user.getPassword(), other.getPassword());
				if (isMatched) {
					Cookie cookie = new Cookie("EmailId", other.getEmailaddress());
					Cookie cookie1 = new Cookie("UserType", other.getUsertype());

					cookie.setMaxAge(7 * 24 * 60 * 60);
					cookie.setSecure(true);
					cookie.setHttpOnly(true);
					cookie.setPath("/");
					cookie1.setMaxAge(7 * 24 * 60 * 60);
					cookie1.setSecure(true);
					cookie1.setPath("/");
					response.addCookie(cookie);
					response.addCookie(cookie1);
					Usertype = other.getUsertype();
					return new ResponseEntity<>(Usertype, HttpStatus.OK);
				}
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
		
		 String password = encryptPassword(object.get("password").getAsString());
		
		 user.setFirst_name(object.get("first_name").getAsString());
		 user.setLast_name(object.get("last_name").getAsString());
		 user.setAddress1(object.get("address1").getAsString());
		 user.setAddress2(object.get("address2").getAsString());
		 user.setCity(object.get("city").getAsString());
		 user.setProvince(object.get("province").getAsString());
		 user.setCountry(object.get("country").getAsString());
		 user.setEmailaddress(object.get("emailaddress").getAsString());
		 user.setPassword(password);
		 user.setRestaurant_name(object.get("restaurant_name").getAsString());
		 user.setLicense_number(object.get("license_number").getAsString());
		 user.setOpens_at(object.get("opens_at").getAsString());
		 user.setCloses_at(object.get("closes_at").getAsString());
		 user.setPhone(object.get("phone").getAsString());
		 user.setZip(object.get("zip").getAsString());
		 user.setUsertype(object.get("user_type").getAsString());
		 try {
			 if(multipartfile!=null)
			 {
			user.setPicture(multipartfile.getBytes());
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 List<AppUser> users = userRepository.findByEmailaddress(user.getEmailaddress());
		 
		 if(!users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);//409
		 }else {
			 AppUser newuser = userRepository.save(user);
			 sendEmail(newuser);
			//savedata(newuser,multipartfile);
			 return new ResponseEntity<>(HttpStatus.OK);
		 }
		 
	}
	
	  private String encryptPassword(String password) {
		  int strength = 10;
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);
		  String encodedPassword = encoder.encode(password);
		 // boolean isMatched = encoder.matches("anubha@123", "$2a$10$P3O8kPYtYrg98f7NdpvFfeKl.D0/68xt/wDqMNWtJUNvVc0NM5m4q");
		  return encodedPassword;	  
	  }
	
	private void sendEmail(AppUser newuser) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props);

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("nurturecommunityp13@gmail.com", false));

			String html = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "  <head>\r\n"
					+ "    <meta charset=\"UTF-8\" />\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
					+ "    <title>Monument</title>\r\n"
					+ "  </head>\r\n"
					+ "  <body>\r\n"
					+ "    <h1>Welcome to Nurture community</h1>\r\n"
					+ "    <h3>\r\n"
					+ "      Our organisation has pleadged to revome food wastage and hunger from the\r\n"
					+ "      canadian community\r\n"
					+ "    </h3>\r\n"
					+ "    \r\n"
					+ "    <h4>Please click on below link to verify the email\r\n"
					+ "    <a href=\"http://localhost:8060\">Verify Email</a></h4>\r\n"
					+ "\r\n"
					+ "    <p>Happy Browsing!</p>\r\n"
					+ "    <p>Thanks and Regads,</p>\r\n"
					+ "    <p>Nurture Community</p>\r\n"
					+ "  </body>\r\n"
					+ "</html>";
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newuser.getEmailaddress()));
			msg.setSubject("Welcome to Nurture Community | Do not reply on this email");
			msg.setContent("Welcome to Nurture Community", "text/html");
			msg.setSentDate(new Date());
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(html, "text/html");
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(multipart);
			Transport.send(msg, "nurturecommunityp13@gmail.com", "nurture@123!");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@PostMapping("/ListOfRestaurantzip")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<List<AppUser>> getByZipCode(@RequestBody String zip) {
	try {
		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject)jsonParser.parse(zip);
		
		
		zip =object.get("city").getAsString();
		List<AppUser> usersByzip = new ArrayList<AppUser>();
		userRepository.findAllByusertype("restaurant").forEach(usersByzip::add);
		List<AppUser> data = new ArrayList<AppUser>();
		for(AppUser obj : usersByzip){
			if (obj.getCity().equals(zip)) {
				data.add(obj);
			}
		}
		if (data.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
				
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		
	}

	@GetMapping("/ListOfRestaurants")
	public ResponseEntity<List<AppUser>> getAllResturants(HttpServletRequest request) {
		try {
			List<AppUser> obj = new ArrayList<AppUser>();
			
			userRepository.findAllByusertype("restaurant").forEach(obj::add);
			
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
		
		@PostMapping(value = "/updateFood")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity updateFood(@RequestBody String myParams,
				HttpServletRequest request) throws IOException {

			//String emailaddress = getCookies(request);
			
			AddFoodDetails foodDetails = new AddFoodDetails();
			
			JsonParser jsonParser = new JsonParser();
			JsonObject object = (JsonObject)jsonParser.parse(myParams);
			
			
			foodDetails.setNumberofPackets(object.get("numberofPackets").getAsInt());
			foodDetails.setFoodDetailId(object.get("id").getAsInt());
			AddFoodDetails newfoodDetails = addFoodDetailsRepository.save(foodDetails);
					
			return new ResponseEntity<>(HttpStatus.OK);
		}
		@PostMapping(value = "/deleteFood")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity deleteFood(@RequestBody String myParams,
				HttpServletRequest request) throws IOException {

			//String emailaddress = getCookies(request);
			
			AddFoodDetails foodDetails = new AddFoodDetails();
			
			JsonParser jsonParser = new JsonParser();
			JsonObject object = (JsonObject)jsonParser.parse(myParams);
			foodDetails.setFoodDetailId(object.get("id").getAsInt());
			addFoodDetailsRepository.delete(foodDetails);				
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
		private void saveUpdateFood(AddFoodDetails newfoodDetails) throws IOException {
			try {
				jdbcTemplate.update(Queries.updateFood, newfoodDetails.getNumberofPackets(),newfoodDetails.getId());

			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
}

