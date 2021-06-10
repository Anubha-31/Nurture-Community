package com.nurturecommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nurturecommunity.services.GetRequestDummy;

@RestController
public class MainController {

	@Autowired
	private GetRequestDummy dummyGetRequest;
	
	@GetMapping("/home")
	public String getRequest() {
		return this.dummyGetRequest.getDummyresponse();
	}
}

