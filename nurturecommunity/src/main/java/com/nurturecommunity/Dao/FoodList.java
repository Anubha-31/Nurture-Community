package com.nurturecommunity.Dao;
import javax.mail.Multipart;

public class FoodList {
	
	private int id;
	private String address;
	private String city;
	private String country;
	private String item_description;
	private String item_name;
	private String location_change;
	private int number_of_packets;
	private String pickupTime;
	private String province;
	private String uploaded_picture;
	private Multipart multi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getLocation_change() {
		return location_change;
	}
	public void setLocation_change(String location_change) {
		this.location_change = location_change;
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
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getUploaded_picture() {
		return uploaded_picture;
	}
	public void setUploaded_picture(String uploaded_picture) {
		this.uploaded_picture = uploaded_picture;
	}
	public Multipart getMulti() {
		return multi;
	}
	public void setMulti(Multipart multi) {
		this.multi = multi;
	}



	


}