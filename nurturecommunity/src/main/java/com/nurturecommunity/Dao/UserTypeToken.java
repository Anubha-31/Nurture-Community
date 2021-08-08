package com.nurturecommunity.Dao;

import com.nurturecommunity.security.JwtResponse;

public class UserTypeToken {

	private String Usertype;
	
	private  JwtResponse token ;

	public String getUsertype() {
		return Usertype;
	}

	@Override
	public String toString() {
		return "UserTypeToken [Usertype=" + Usertype + ", token=" + token + "]";
	}


	public void setUsertype(String usertype) {
		Usertype = usertype;
	}

	public JwtResponse getToken() {
		return token;
	}

	public void setToken(JwtResponse jwtResponse) {
		this.token = jwtResponse;
	}
}
