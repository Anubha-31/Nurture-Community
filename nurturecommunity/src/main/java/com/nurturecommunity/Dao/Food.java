package com.nurturecommunity.Dao;


import java.sql.Time;

public class Food {
String resturantName;
String itemName;
String description;
String picturePath;
int foodCount;
String pickaupLocation;
Time expirationTime;


public String getPickaupLocation() {
	return pickaupLocation;
}
public void setPickaupLocation(String pickaupLocation) {
	this.pickaupLocation = pickaupLocation;
}
public String getResturantName() {
	return resturantName;
}
public void setResturantName(String resturantName) {
	this.resturantName = resturantName;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getPicturePath() {
	return picturePath;
}
public void setPicturePath(String picturePath) {
	this.picturePath = picturePath;
}
public int getFoodCount() {
	return foodCount;
}
public void setFoodCount(int foodCount) {
	this.foodCount = foodCount;
}
public Time getExpirationTime() {
	return expirationTime;
}
public void setExpirationTime(Time expirationTime) {
	this.expirationTime = expirationTime;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}


}
