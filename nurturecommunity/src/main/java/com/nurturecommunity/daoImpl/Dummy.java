package com.nurturecommunity.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class Dummy {

    @Autowired
    private JdbcTemplate jdbcTemplate;
     
       public void run(String... args) throws Exception {
        String sql = "Select count(*) from customers";

        RowCallbackHandler rse;
        SqlRowSet rowSet =  jdbcTemplate.queryForRowSet(sql);
	if (rowSet.next())
	{
		System.out.println("yes   ----");
	}
   
	
    }
}
