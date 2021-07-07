package com.nurturecommunity.Dao;


public class FoodList {
	
	private int id;
	private String address;
	private String license;
	private String restaurant_name;
	private String item_name;
	private String item_description;
	private int number_of_packets;
	private String pickupTime;
	private String uploaded_picture;

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public int getNumber_of_packets() {
		return number_of_packets;
	}
	public void setNumber_of_packets(int number_of_packets) {
		this.number_of_packets = number_of_packets;
	}
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getUploaded_picture() {
		return uploaded_picture;
	}
	public void setUploaded_picture(String uploaded_picture) {
		this.uploaded_picture = uploaded_picture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	


}