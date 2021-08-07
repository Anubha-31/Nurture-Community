package com.nurturecommunity.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
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

import org.apache.tomcat.util.codec.binary.Base64;
//import jdk.internal.org.jline.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nurturecommunity.Dao.AddFoodDetails;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.ContactUsDetails;
import com.nurturecommunity.Dao.FoodList;
import com.nurturecommunity.Dao.Order;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.constant.Queries;
import com.nurturecommunity.repository.AddFoodDetailsRepository;
import com.nurturecommunity.repository.ContactUsRepository;
import com.nurturecommunity.repository.OrderRepository;
import com.nurturecommunity.repository.UserRepository;
import com.nurturecommunity.services.GetRequest;

@RestController
@CrossOrigin(origins = "http://localhost:8060/", allowedHeaders = "*",allowCredentials="true")
public class MainController {

	@Autowired
	private GetRequest getRequest;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	AddFoodDetailsRepository addFoodDetailsRepository;
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private Environment env;
	
	@Autowired
	private com.nurturecommunity.model.LoginDetails Login;
	
	@Autowired
	ContactUsRepository contactUsrepo;
	
	@GetMapping("/login")
	public List<User> getRequest() throws Exception {
		return this.getRequest.getLoginresponse();
	}
	
	
	
	@GetMapping("/Listoffooditems")
	synchronized public ResponseEntity<List<FoodList>> getFoodList(HttpServletRequest request) throws Exception {

		String Cookie = getCookies(request);
		
		if (Cookie != null){
			return new ResponseEntity<>(this.getRequest.getFoodresponse(Cookie), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
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
		List<AppUser> users1 = userRepository.findAllByusertype(user.getUsertype());

		String Usertype = "Failure";

		for (AppUser other : users) {
			if (other.getEmailaddress().equalsIgnoreCase(user.getEmailaddress())) {
				if (user.isVerificationComplete()) {
					int strength = 10;
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);
					boolean isMatched = encoder.matches(user.getPassword(), other.getPassword());
					if (isMatched) {
						Cookie cookie = new Cookie("EmailId", other.getEmailaddress());
						Cookie cookie1 = new Cookie("UserType", other.getUsertype());

						cookie.setMaxAge(7 * 24 * 60 * 60);
						cookie.setSecure(true);
						// cookie.setHttpOnly(true);
						cookie.setPath("/");
						cookie1.setMaxAge(7 * 24 * 60 * 60);
						cookie1.setSecure(true);
						cookie1.setPath("/");
						response.addCookie(cookie);
						response.addCookie(cookie1);
						Usertype = other.getUsertype();
						return new ResponseEntity<>(Usertype, HttpStatus.OK);
					}
				}else {
					return new ResponseEntity<>(Usertype, HttpStatus.CONFLICT);
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/verify/login")
	public ResponseEntity<String> verifyUser(HttpServletRequest request) {
		String key = env.getProperty("cipher.key");

		StringBuffer jb = new StringBuffer();
		String line = null;

		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject) jsonParser.parse(jb.toString());

		String urlData = object.get("urldata").getAsString();

		String data2 = simpleDecrypt(urlData, key);

		Optional<AppUser> user = userRepository.findById(Long.parseLong(data2));

		if (user.isPresent()) {
			AppUser updatedUser = user.get();
			updatedUser.setVerificationComplete(true);
			userRepository.save(updatedUser);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	public String simpleEncrypt(String text, String key) {
		
		String data = "";
		Base64 base64 = new Base64(true);
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF8"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(cipher.ENCRYPT_MODE, keySpec);
			data = base64.encodeToString(cipher.doFinal(text.getBytes("UTF8")));
			//data = new String(encrypted);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	public String simpleDecrypt(String encryptedtext, String key) {
		String data = "";
		Base64 base64 = new Base64(true);
		byte[] encryptedDataBase64 = base64.decodeBase64(encryptedtext);
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF8"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(cipher.DECRYPT_MODE, keySpec);
			byte[] decrypted = cipher.doFinal(encryptedDataBase64);
			data = new String(decrypted);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
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

			String html = getEmailBody(newuser);
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



	private String getEmailBody(AppUser user) {
		
		Long userId = user.getId();
		String key = env.getProperty("cipher.key");
		String path = env.getProperty("settings.cors_origin");
		String encryptedId = simpleEncrypt(userId.toString(), key);
		String pathHref = path+"/login/"+encryptedId;
		
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
				+ "<html\r\n"
				+ "  xmlns=\"http://www.w3.org/1999/xhtml\"\r\n"
				+ "  xmlns:v=\"urn:schemas-microsoft-com:vml\"\r\n"
				+ "  xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n"
				+ ">\r\n"
				+ "  <head>\r\n"
				+ "    <title>Email</title>\r\n"
				+ "    <style type=\"text/css\" media=\"screen\">\r\n"
				+ "      @media only screen and (max-device-width: 480px),\r\n"
				+ "        only screen and (max-width: 480px) {\r\n"
				+ "        .mobile-shell {\r\n"
				+ "          width: 100% !important;\r\n"
				+ "          min-width: 100% !important;\r\n"
				+ "        }\r\n"
				+ "        .center {\r\n"
				+ "          margin: 0 auto !important;\r\n"
				+ "        }\r\n"
				+ "        .container {\r\n"
				+ "          padding: 20px 10px !important;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .td {\r\n"
				+ "          width: 100% !important;\r\n"
				+ "          min-width: 100% !important;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        .p30-15 {\r\n"
				+ "          padding: 30px 15px !important;\r\n"
				+ "        }\r\n"
				+ "        .m-hide {\r\n"
				+ "          display: none !important;\r\n"
				+ "          width: 0 !important;\r\n"
				+ "          height: 0 !important;\r\n"
				+ "          font-size: 0 !important;\r\n"
				+ "          line-height: 0 !important;\r\n"
				+ "          min-height: 0 !important;\r\n"
				+ "        }\r\n"
				+ "      }\r\n"
				+ "    </style>\r\n"
				+ "  </head>\r\n"
				+ "  <body\r\n"
				+ "    class=\"body\"\r\n"
				+ "    style=\"\r\n"
				+ "      padding: 0 !important;\r\n"
				+ "      margin: 0 !important;\r\n"
				+ "      display: block !important;\r\n"
				+ "      min-width: 100% !important;\r\n"
				+ "      width: 100% !important;\r\n"
				+ "      background:white;\r\n"
				+ "      -webkit-text-size-adjust: none;\r\n"
				+ "    \"\r\n"
				+ "  >\r\n"
				+ "    <table\r\n"
				+ "      width=\"100%\"\r\n"
				+ "      border=\"0\"\r\n"
				+ "      cellspacing=\"0\"\r\n"
				+ "      cellpadding=\"0\"\r\n"
				+ "      style=\"background-color: white;\"\r\n"
				+ "    >\r\n"
				+ "      <tr>\r\n"
				+ "        <td align=\"center\" valign=\"top\" style=\"padding-bottom: 0px\">\r\n"
				+ "          <table\r\n"
				+ "            width=\"850\"\r\n"
				+ "            border=\"0\"\r\n"
				+ "            cellspacing=\"0\"\r\n"
				+ "            cellpadding=\"0\"\r\n"
				+ "            class=\"mobile-shell\"\r\n"
				+ "          >\r\n"
				+ "            <tr>\r\n"
				+ "              <td\r\n"
				+ "                class=\"td container\"\r\n"
				+ "                style=\"\r\n"
				+ "                  width: 850px;\r\n"
				+ "                  min-width: 850px;\r\n"
				+ "                  font-size: 0pt;\r\n"
				+ "                  line-height: 0pt;\r\n"
				+ "                  margin: 0;\r\n"
				+ "                  font-weight: normal;\r\n"
				+ "                  padding: 55px 0px;\r\n"
				+ "                \"\r\n"
				+ "              >\r\n"
				+ "                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                  <tr>\r\n"
				+ "                    <td>\r\n"
				+ "                      <table\r\n"
				+ "                        width=\"100%\"\r\n"
				+ "                        border=\"0\"\r\n"
				+ "                        cellspacing=\"0\"\r\n"
				+ "                        cellpadding=\"0\"\r\n"
				+ "                      >\r\n"
				+ "                        <tr>\r\n"
				+ "                          <td\r\n"
				+ "                            class=\"tbrr p30-15\"\r\n"
				+ "                            style=\"\r\n"
				+ "                              padding: 30px 30px;\r\n"
				+ "                              border-radius: 26px 26px 26px 26px;\r\n"
				+ "                            \"\r\n"
				+ "                            bgcolor=\"white\"\r\n"
				+ "                          >\r\n"
				+ "                            <table\r\n"
				+ "                              width=\"100%\"\r\n"
				+ "                              border=\"0\"\r\n"
				+ "                              cellspacing=\"0\"\r\n"
				+ "                              cellpadding=\"0\"\r\n"
				+ "                            >\r\n"
				+ "                              <tr>\r\n"
				+ "                                <td\r\n"
				+ "                                  class=\"h1 pb25\"\r\n"
				+ "                                  style=\"\r\n"
				+ "                                    color: #0f0a0a;\r\n"
				+ "                                    font-family: 'Muli', Arial, sans-serif;\r\n"
				+ "                                    font-size: 40px;\r\n"
				+ "                                    line-height: 46px;\r\n"
				+ "                                    text-align: center;\r\n"
				+ "                                    padding-bottom: 25px;\r\n"
				+ "                                  \"\r\n"
				+ "                                >\r\n"
				+ "                                  Welcome To Nurture Community\r\n"
				+ "                                </td>\r\n"
				+ "                              </tr>\r\n"
				+ "                              <tr>\r\n"
				+ "                                <td\r\n"
				+ "                                  class=\"text-center pb25\"\r\n"
				+ "                                  style=\"\r\n"
				+ "                                    color: #353431;\r\n"
				+ "                                    font-family: 'Muli', Arial, sans-serif;\r\n"
				+ "                                    font-size: 18px;\r\n"
				+ "                                    line-height: 30px;\r\n"
				+ "                                    text-align: center;\r\n"
				+ "                                    padding-bottom: 25px;\r\n"
				+ "                                  \"\r\n"
				+ "                                >\r\n"
				+ "                                  We have pledged to remove the food wastage and\r\n"
				+ "                                  hunger from the canadian community. <span\r\n"
				+ "                                    class=\"m-hide\"\r\n"
				+ "                                    ><br /></span\r\n"
				+ "                                  >If you are a restaurant owner, we thankyou for giving back to the Community\r\n"
				+ "                                  <span\r\n"
				+ "                                    class=\"m-hide\"\r\n"
				+ "                                    ><br /></span>\r\n"
				+ "                                  If you are someone in need, we are pleased that we could help.\r\n"
				+ "                                  <span\r\n"
				+ "                                    class=\"m-hide\"\r\n"
				+ "                                    ><br /></span>\r\n"
				+ "                                  Join Us in this mission, Click the below link to verify your email\r\n"
				+ "                                  \r\n"
				+ "                                </td>\r\n"
				+ "                              </tr>\r\n"
				+ "                              <tr>\r\n"
				+ "                                <td align=\"center\">\r\n"
				+ "                                  <table\r\n"
				+ "                                    class=\"center\"\r\n"
				+ "                                    border=\"0\"\r\n"
				+ "                                    cellspacing=\"0\"\r\n"
				+ "                                    cellpadding=\"0\"\r\n"
				+ "                                    style=\"text-align: center\"\r\n"
				+ "                                  >\r\n"
				+ "                                    <tr>\r\n"
				+ "                                      <td\r\n"
				+ "                                        class=\"pink-button text-button\"\r\n"
				+ "                                        style=\"\r\n"
				+ "                                          background: #e27208;\r\n"
				+ "                                          color: #c1cddc;\r\n"
				+ "                                          font-family: 'Muli', Arial, sans-serif;\r\n"
				+ "                                          font-size: 14px;\r\n"
				+ "                                          line-height: 18px;\r\n"
				+ "                                          padding: 12px 30px;\r\n"
				+ "                                          text-align: center;\r\n"
				+ "                                          border-radius: 0px 22px 22px 22px;\r\n"
				+ "                                          font-weight: bold;\r\n"
				+ "                                        \"\r\n"
				+ "                                      >\r\n"
				+ "                                        <a\r\n"
				+ "                                          href=\""+pathHref+"\"\r\n"
				+ "                                          target=\"_blank\"\r\n"
				+ "                                          style=\"\r\n"
				+ "                                            color: #ffffff;\r\n"
				+ "                                            text-decoration: none;\r\n"
				+ "                                          \"\r\n"
				+ "                                          ><span\r\n"
				+ "                                            style=\"\r\n"
				+ "                                              color: #ffffff;\r\n"
				+ "                                              text-decoration: none;\r\n"
				+ "                                            \"\r\n"
				+ "                                            >CLICK HERE</span\r\n"
				+ "                                          ></a\r\n"
				+ "                                        >\r\n"
				+ "                                      </td>\r\n"
				+ "                                    </tr>\r\n"
				+ "                                  </table>\r\n"
				+ "                                </td>\r\n"
				+ "                              </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                          </td>\r\n"
				+ "                        </tr>\r\n"
				+ "                      </table>\r\n"
				+ "                    </td>\r\n"
				+ "                  </tr>\r\n"
				+ "                </table>\r\n"
				+ "              </td>\r\n"
				+ "            </tr>\r\n"
				+ "          </table>\r\n"
				+ "        </td>\r\n"
				+ "      </tr>\r\n"
				+ "    </table>\r\n"
				+ "  </body>\r\n"
				+ "</html>\r\n"
				+ "";
		return html;
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
	public ResponseEntity<List<AppUser>> getByZipCode(@RequestBody String zip,HttpServletRequest request) {
	try {
		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject)jsonParser.parse(zip);
		String userZip = "";
		zip =object.get("city").getAsString();
		String Cookie = getCookies(request);
		List<AppUser> user = userRepository.findByEmailaddress(Cookie);
		if (user.size() != 0) {
			userZip = user.get(0).getZip();
		}
		List<AppUser> usersByzip = new ArrayList<AppUser>();
		userRepository.findAllByusertype("restaurant").forEach(usersByzip::add);
		List<AppUser> data = new ArrayList<AppUser>();
		for(AppUser obj : usersByzip){
			if (obj.getCity().equals(zip)) {
				
				String tempDistance  = toDistance(userZip,obj.getZip());
				double Doubletemp = Double.parseDouble(tempDistance);
				DecimalFormat numberFormat = new DecimalFormat("#.00");
				
				numberFormat.format(Doubletemp);
				obj.setDistance(String.valueOf(Doubletemp));
				obj.setDistance(tempDistance);
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
			String userZip = "";
			String Cookie = getCookies(request);
//			String Cookie = "arshdeepk61@gmail.com";
			List<AppUser> user = userRepository.findByEmailaddress(Cookie);
			if (user.size() != 0) {
				userZip = user.get(0).getZip();
			}
			List<AppUser> usersByzip = new ArrayList<AppUser>();
			userRepository.findAllByusertype("restaurant").forEach(obj::add);
			for(AppUser o : obj){
				
					
				String tempDistance  = toDistance(userZip,o.getZip());
				double Doubletemp = Double.parseDouble(tempDistance);
				DecimalFormat numberFormat = new DecimalFormat("#.00");
				
				numberFormat.format(Doubletemp);
				o.setDistance(String.valueOf(Doubletemp));
				usersByzip.add(o);
					
				
			}
			
			
			if (usersByzip .isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usersByzip, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	

		String getCookies(HttpServletRequest request) {

			String emailid = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null){
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("EmailId")) {
							emailid = cookie.getValue();
						}
				}
				return emailid;
			}else {
				return null;
			}
		}
	
		
    
		@PostMapping(value = "/addFoodDetails", consumes = "multipart/form-data")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity AddFood(@RequestParam("model") String myParams,
				@RequestParam(value = "uploadedPicture", required = false) MultipartFile multipartfile,
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
			 try {
				 if(multipartfile!=null)
				 {
					 foodDetails.setpicture(multipartfile.getBytes());
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AddFoodDetails newfoodDetails = addFoodDetailsRepository.save(foodDetails);
				
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		

		
		@PostMapping(value = "/ContactUs")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity saveQueries(@Valid @RequestBody ContactUsDetails user, HttpServletResponse response) {
			contactUsrepo.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@PostMapping(value = "/updateFood")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity updateFood(@RequestBody String myParams,
				HttpServletRequest request) throws IOException {

			//String emailaddress = getCookies(request);
			
			
			JsonParser jsonParser = new JsonParser();
			JsonObject object = (JsonObject)jsonParser.parse(myParams);
			
			AddFoodDetails foodDetails = addFoodDetailsRepository.findByfoodDetailId(object.get("id").getAsInt());
			foodDetails.setNumberofPackets(object.get("numberofPackets").getAsInt());
			addFoodDetailsRepository.save(foodDetails);
					
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

//		private void saveFooddata(AddFoodDetails newfoodDetails, MultipartFile multipartfile) {
//			try {
//				jdbcTemplate.update(Queries.AddFoodFiles, newfoodDetails.getFoodDetailId(), multipartfile.getName(),
//						multipartfile.getBytes());
//
//			} catch (DataAccessException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		private void saveUpdateFood(AddFoodDetails newfoodDetails) throws IOException {
			try {
				jdbcTemplate.update(Queries.updateFood, newfoodDetails.getNumberofPackets(),newfoodDetails.getId());
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	@PostMapping(value = "/profile/{id}")
	public ResponseEntity retrieveResaurant(@PathVariable Long id) {
		try {
			
			List<AppUser> users =userRepository.findAllById(id);
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	

	}
	

	
	public static String toDistance(String userzip1, String reszip2) throws IOException {
		
		String Search = "https://api.zip-codes.com/ZipCodesAPI.svc/1.0/CalculateDistance/ByZip?fromzipcode="+userzip1+"&tozipcode="+reszip2+"&key=7AJTINS13OS1CEJLJCAR";
		URL url = new URL(Search);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
			
			}
		in.close();
		con.disconnect();
		JsonParser jsonParser = new JsonParser();
		JsonObject object = (JsonObject)jsonParser.parse(content.toString());
		String distance = object.get("DistanceInKm").getAsString();
		return distance;
		
	}
		
	@PostMapping(value = "/restaurant/{id}")
	public ResponseEntity retrieverestaurantsFood(@PathVariable Long id) {
try {
			
			List<AddFoodDetails> users =addFoodDetailsRepository.findAllByid(id);
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	

	}
	
	@PostMapping(value = "/customer/claim-food")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity claimFood(@RequestBody String myParams, HttpServletRequest request) throws IOException {
		
		try {
            JsonParser jsonParser = new JsonParser();
            JsonObject object = (JsonObject)jsonParser.parse(myParams);

            Order order = new Order();

            order.setCustomerEmail(getCookies(request));
            order.setFoodId(object.get("foodId").getAsInt());
            order.setRestaurantId(object.get("restaurantId").getAsInt());

            Order newOrder = orderRepository.save(order);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PostMapping(value = "/order/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateOrder(@RequestBody String myParams, HttpServletRequest request) throws IOException {
		

            JsonParser jsonParser = new JsonParser();
            JsonObject object = (JsonObject)jsonParser.parse(myParams);
            
            System.out.println(object.get("orderId").getAsInt());
            System.out.println(object.get("foodId").getAsInt());


	}
}

