package com.nurturecommunity.services;

import java.util.List;

import com.nurturecommunity.Dao.User;
import com.nurturecommunity.Dao.FoodList;

public interface GetRequest {

	public List<User> getLoginresponse() throws Exception;
	public List<FoodList> getFoodresponse(String Cookie) throws Exception;

}
