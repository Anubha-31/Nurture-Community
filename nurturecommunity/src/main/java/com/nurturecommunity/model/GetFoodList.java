package com.nurturecommunity.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.nurturecommunity.Dao.Food;
import com.nurturecommunity.Dao.User;
import com.nurturecommunity.constant.Queries;

@Component
public class GetFoodList {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	com.nurturecommunity.Dao.Food food = new com.nurturecommunity.Dao.Food();

	public List<Food> getFoodList() throws Exception {

		List<Food> list=new ArrayList<Food>();  
		 return (List<Food>) jdbcTemplate.query(Queries.FoodListsql,new ResultSetExtractor<List<Food>>(){  
			    public List<Food> extractData(ResultSet rs) throws SQLException,  
			            DataAccessException {  
			      
			        
			        while(rs.next()){  
			        Food e=new Food();
			        e.setResturantName(rs.getString(2));
			        e.setItemName(rs.getString(3));
			        e.setDescription(rs.getString(4));
			        e.setPicturePath(rs.getString(5));
			        e.setFoodCount( rs.getInt(6));
			        e.setPickaupLocation(rs.getString(7));
			        e.setExpirationTime(rs.getTime(8));
			        list.add(e);  
			        }  
			        return list;  
			        }  
			    });
	}

}
