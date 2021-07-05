package com.nurturecommunity.Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="FoodDetails")
public class AddFoodDetails {

	
	
	@Id
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
	
	@Column(name="address")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="foodId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String foodDetailId;

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
				+ address + ", foodDetailId=" + foodDetailId + "]";
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
		this.address = address;
	}

	public AddFoodDetails()
	{
		
	}
}
