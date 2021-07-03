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

@Component
public class GetFoodList {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public List<FoodList> getFoodList() throws Exception {
		
		
		List<FoodList> list = new ArrayList<FoodList>();  
		 return (List<FoodList>) jdbcTemplate.query(Queries.FoodListsql,new ResultSetExtractor<List<FoodList>>(){  
			    public List<FoodList> extractData(ResultSet rs) throws SQLException,  
			            DataAccessException {  
			      
			        
			        while(rs.next()){  
			        FoodList e = new FoodList();
			        e.setId(rs.getLong(1));
			        e.setRestaurantName(rs.getString(7));
			        e.setItemName(rs.getString(3));
			        e.setItemDescription(rs.getString(2));
			        e.setUploadedPicture(rs.getString(8));
			        e.setNumberofPackets( rs.getInt(5));
			        e.setLocationChange(rs.getString(4));
			        e.setPickupTime(rs.getString(6));
			        list.add(e);  
			        }  
			        return list;
			    	}
			     });

	
		 }

}