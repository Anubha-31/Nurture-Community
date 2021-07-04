package com.nurturecommunity.Dao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="users")
public class AppUser {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
//    private String user_type;
    private String first_name;
    private String last_name;
    private String email_address;
    private String password;
    private String restaurant_name;
    private String license_number;
    private String address;
    private String phone;
    private String zip;
    private String user_type;
    private String opens_at;
    private String closes_at;
    private @NotBlank boolean loggedin;
	
	// @Lob private byte[] cover_image;
	 
   // private MultipartFile file;

    public AppUser(){

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getOpens_at() {
		return opens_at;
	}

	public void setOpens_at(String opens_at) {
		this.opens_at = opens_at;
	}

	public String getCloses_at() {
		return closes_at;
	}

	public void setCloses_at(String closes_at) {
		this.closes_at = closes_at;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email_address="
				+ email_address + ", password=" + password + ", restaurant_name=" + restaurant_name
				+ ", license_number=" + license_number + ", address=" + address + ", phone=" + phone + ", zip=" + zip
				+ ", user_type=" + user_type + ", opens_at=" + opens_at + ", closes_at=" + closes_at + ", loggedin="
				+ loggedin + "]";
	}

	public AppUser(String first_name, String last_name, String email_address, String password, String restaurant_name,
			String license_number, String address, String phone, String zip, String user_type, String opens_at,
			String closes_at) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_address = email_address;
		this.password = password;
		this.restaurant_name = restaurant_name;
		this.license_number = license_number;
		this.address = address;
		this.phone = phone;
		this.zip = zip;
		this.user_type = user_type;
		this.opens_at = opens_at;
		this.closes_at = closes_at;
	}

	
	
}
