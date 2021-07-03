package com.nurturecommunity.Dao;


public class FoodList {

	private long id;
	private String restaurantName;
	private String itemName;
	private String itemDescription;
	private String uploadedPicture;
	private int numberofPackets;
	private String locationChange;
	private String pickupTime;
	
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getLocationChange() {
		return locationChange;
	}
	public void setLocationChange(String locationChange) {
		this.locationChange = locationChange;
	}
	public int getNumberofPackets() {
		return numberofPackets;
	}
	public void setNumberofPackets(int numberofPackets) {
		this.numberofPackets = numberofPackets;
	}
	public String getUploadedPicture() {
		return uploadedPicture;
	}
	public void setUploadedPicture(String uploadedPicture) {
		this.uploadedPicture = uploadedPicture;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


}