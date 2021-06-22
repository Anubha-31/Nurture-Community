package com.nurturecommunity.services;

import java.util.List;

import com.nurturecommunity.Dao.Food;
import com.nurturecommunity.Dao.User;

public interface GetRequest {

	public List<User> getLoginresponse() throws Exception;
	public List<Food> getFoodList() throws Exception;
}
