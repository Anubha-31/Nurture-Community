package com.nurturecommunity.Dao;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class AppUser {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
//    private String usertype;
    private String first_name;
    private String last_name;
    private String emailaddress;
    private String password;
    private String restaurant_name;
    private String license_number;
    private String address1;
	private String address2;
	private String city;
	private String province;
	private String country;
    private String phone;
    private String zip;
    private String distance;
    public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	private String usertype;
    private String opens_at;
    private String closes_at;
    private @NotBlank boolean loggedin;
	
    private byte[] picture ;
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
	
	

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
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

	public AppUser(String first_name, String last_name, String emailaddress, String password, String restaurant_name,
			String license_number, String address1, String address2, String city, String province, String country,
			String phone, String zip, String usertype, String opens_at, String closes_at, @NotBlank boolean loggedin,
			byte[] picture, String distance) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.emailaddress = emailaddress;
		this.password = password;
		this.restaurant_name = restaurant_name;
		this.license_number = license_number;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.country = country;
		this.phone = phone;
		this.zip = zip;
		this.usertype = usertype;
		this.opens_at = opens_at;
		this.closes_at = closes_at;
		this.loggedin = loggedin;
		this.picture = picture;
		this.distance = distance;
	}


	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", emailaddress="
				+ emailaddress + ", password=" + password + ", restaurant_name=" + restaurant_name + ", license_number="
				+ license_number + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city
				+ ", province=" + province + ", country=" + country + ", phone=" + phone + ", zip=" + zip
				+ ", distance=" + distance + ", usertype=" + usertype + ", opens_at=" + opens_at + ", closes_at="
				+ closes_at + ", loggedin=" + loggedin + ", picture=" + Arrays.toString(picture) + "]";
	}

}
