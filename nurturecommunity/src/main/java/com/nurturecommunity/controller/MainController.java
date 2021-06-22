package com.nurturecommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nurturecommunity.Dao.Food;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.services.GetRequest;

@RestController
public class MainController {

	@Autowired
	private GetRequest getRequest;
	
	@Autowired
	private com.nurturecommunity.model.LoginDetails Login;
	
	@GetMapping("/login")
	public List<User> getRequest() throws Exception {
		return this.getRequest.getLoginresponse();
	}
	
	@GetMapping("/foodlist")
	public List<Food> getRequestFoodList() throws Exception {
		return this.getRequest.getFoodList();
	}
	
}

