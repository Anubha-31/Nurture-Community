package com.nuturecommunity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nurturecommunity.Dao.FoodList;

public class ResturantMapper implements RowMapper<FoodList> {

	@Override
	public FoodList mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FoodList e = new FoodList();
      
        if (rs.getString(1) != null && rs.getString(1).equals("Yes")) {
      	
        	e.setAddress(rs.getString(2));
        	
        }else {
        	
        	e.setAddress(rs.getString(3));
        	
        }
	        e.setLicense(rs.getString(4));
	        e.setRestaurant_name(rs.getString(5));
	        e.setItem_name(rs.getString(6));
	        e.setItem_description(rs.getString(7));
	        e.setNumber_of_packets(rs.getInt(8));
	        e.setPickupTime(rs.getString(9));
	        e.setUploaded_picture(rs.getString(10));
	        
   
 
		
		return e;
	}

}
