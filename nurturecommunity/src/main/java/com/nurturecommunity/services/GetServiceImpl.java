package com.nurturecommunity.services;

import org.springframework.stereotype.Service;

@Service
public class GetServiceImpl implements GetRequestDummy{

	@Override
	public String getDummyresponse() {
		return "Hello World";
	}

}
