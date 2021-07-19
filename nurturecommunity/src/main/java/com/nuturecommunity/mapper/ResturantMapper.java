package com.nuturecommunity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nurturecommunity.Dao.FoodList;

public class ResturantMapper implements RowMapper<FoodList> {

	@Override
	public FoodList mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FoodList e = new FoodList();
		e.setId(rs.getInt(1));
        if (rs.getString(2) != null && rs.getString(8).equals("Yes")) {
      	
        	e.setAddress(rs.getString(3));
        	
        }else {
        	
        	e.setAddress(rs.getString(2));
        	
        }	
        
	    e.setCity(rs.getString(4));
	    e.setCountry(rs.getString(5));
	    e.setItem_description(rs.getString(6));
	    e.setItem_name(rs.getString(7));
	    e.setNumber_of_packets(rs.getInt(9));
	    e.setPickupTime(rs.getString(10));
	    e.setUploaded_picture(rs.getString(11));
   
 
		
		return e;
	}

}
