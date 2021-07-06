package com.nurturecommunity.Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Food_Details")
public class AddFoodDetails {

	@Column(name="id")
	private long id;

	@Column(name="restaurantName")
	private String restaurantName;
	
	@Column(name="itemName")
	private String itemName;
	
	@Column(name="itemDescription")
	private String itemDescription;
	
	@Column(name="uploadedPicture")
	private String uploadedPicture;
	
	@Column(name="numberofPackets")
	private int numberofPackets;
	
	@Column(name="locationChange")
	private String locationChange;
	
	@Column(name="pickupTime")
	private String pickupTime;
	
	@Column(name="address1")
	private String address1;
	@Column(name="address2")
	private String address2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="province")
	private String province;
	
	@Column(name="country")
	private String country;

	@Id
	@Column(name="foodId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String foodDetailId;

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

	public String getFoodDetailId() {
		return foodDetailId;
	}

	public void setFoodDetailId(String foodDetailId) {
		this.foodDetailId = foodDetailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getUploadedPicture() {
		return uploadedPicture;
	}

	public void setUploadedPicture(String uploadedPicture) {
		this.uploadedPicture = uploadedPicture;
	}

	public int getNumberofPackets() {
		return numberofPackets;
	}

	public void setNumberofPackets(int numberofPackets) {
		this.numberofPackets = numberofPackets;
	}

	public String getLocationChange() {
		return locationChange;
	}

	public void setLocationChange(String locationChange) {
		this.locationChange = locationChange;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	

	@Override
	public String toString() {
		return "AddFoodDetails [id=" + id + ", restaurantName=" + restaurantName + ", itemName=" + itemName
				+ ", itemDescription=" + itemDescription + ", uploadedPicture=" + uploadedPicture + ", numberofPackets="
				+ numberofPackets + ", locationChange=" + locationChange + ", pickupTime=" + pickupTime + ", address="
				+ address1 + ", foodDetailId=" + foodDetailId + "]";
	}

	
	public AddFoodDetails(long id, String restaurantName, String itemName, String itemDescription,
			String uploadedPicture, int numberofPackets, String locationChange, String pickupTime, String address) {
		super();
		this.id = id;
		this.restaurantName = restaurantName;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.uploadedPicture = uploadedPicture;
		this.numberofPackets = numberofPackets;
		this.locationChange = locationChange;
		this.pickupTime = pickupTime;
		this.address1 = address;
	}

	public AddFoodDetails()
	{
		
	}
}
