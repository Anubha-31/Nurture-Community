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

import com.nurturecommunity.Dao.User;
import com.nurturecommunity.constant.Queries;

@Component
public class LoginDetails {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	com.nurturecommunity.Dao.User user = new com.nurturecommunity.Dao.User();

	public List<User> getUserdata() throws Exception {

		List<User> list=new ArrayList<User>();  
		 return (List<User>) jdbcTemplate.query(Queries.Loginsql,new ResultSetExtractor<List<User>>(){  
			    public List<User> extractData(ResultSet rs) throws SQLException,  
			            DataAccessException {  
			      
			        
			        while(rs.next()){  
			        User e=new User();  
			        e.setEmailid(rs.getString(1));  
			        e.setName(rs.getString(2));  
			        e.setDob(rs.getString(3)); 
			        e.setPassword(rs.getString(4));
			        list.add(e);  
			        }  
			        return list;  
			        }  
			    });
	}

}
