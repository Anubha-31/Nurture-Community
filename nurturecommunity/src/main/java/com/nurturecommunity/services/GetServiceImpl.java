package com.nurturecommunity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nurturecommunity.Dao.Food;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.model.GetFoodList;
import com.nurturecommunity.model.LoginDetails;

@Service
public class GetServiceImpl implements GetRequest{

	
	@Autowired
	private LoginDetails login;
	@Autowired
	private GetFoodList foodList;
	
	@Override
	public List<User> getLoginresponse() throws Exception {
		return login.getUserdata();
	}
	@Override
	public List<Food> getFoodList() throws Exception {
		return foodList.getFoodList();
	}

}
