package com.nurturecommunity.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.nurturecommunity.Dao.FoodList;
import com.nurturecommunity.constant.Queries;
import com.nuturecommunity.mapper.ResturantMapper;

@Component
public class GetFoodList {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	

	public List<FoodList> getFoodList(String Cookie) throws Exception {
		List<FoodList> list =   jdbcTemplate.query(
                				Queries.foodListsql,
                				new ResturantMapper(),new Object[] { Cookie });
		 
		return list;

	
		 }

}