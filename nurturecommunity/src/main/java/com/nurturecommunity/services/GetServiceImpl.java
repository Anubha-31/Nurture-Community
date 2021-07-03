package com.nurturecommunity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurturecommunity.Dao.User;
import com.nurturecommunity.model.LoginDetails;

@Service
public class GetServiceImpl implements GetRequest{

	
	@Autowired
	private LoginDetails login;
	
	@Override
	public List<User> getLoginresponse() throws Exception {
		return login.getUserdata();
	}

}
