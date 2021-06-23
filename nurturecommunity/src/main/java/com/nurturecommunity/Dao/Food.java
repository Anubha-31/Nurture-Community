package com.nurturecommunity.Dao;


import java.sql.Time;
import javax.persistence.*;


@Entity
@Table(name="food")
public class Food {
	

	
	@Id
	@GeneratedValue
	int restaurant_id;
	String restaurant_name;
	String food_name;
	String item_description;
	String upload_picture;
	int quantity;
	String pickup_location;
	Time pickup_time;
	
	
	
	@Override
	public String toString() {
		return "Food [restaurant_id=" + restaurant_id + ", restaurant_name=" + restaurant_name + ", food_name="
				+ food_name + ", item_description=" + item_description + ", upload_picture=" + upload_picture
				+ ", quantity=" + quantity + ", pickup_location=" + pickup_location + ", pickup_time=" + pickup_time
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food_name == null) ? 0 : food_name.hashCode());
		result = prime * result + ((item_description == null) ? 0 : item_description.hashCode());
		result = prime * result + ((pickup_location == null) ? 0 : pickup_location.hashCode());
		result = prime * result + ((pickup_time == null) ? 0 : pickup_time.hashCode());
		result = prime * result + quantity;
		result = prime * result + restaurant_id;
		result = prime * result + ((restaurant_name == null) ? 0 : restaurant_name.hashCode());
		result = prime * result + ((upload_picture == null) ? 0 : upload_picture.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (food_name == null) {
			if (other.food_name != null)
				return false;
		} else if (!food_name.equals(other.food_name))
			return false;
		if (item_description == null) {
			if (other.item_description != null)
				return false;
		} else if (!item_description.equals(other.item_description))
			return false;
		if (pickup_location == null) {
			if (other.pickup_location != null)
				return false;
		} else if (!pickup_location.equals(other.pickup_location))
			return false;
		if (pickup_time == null) {
			if (other.pickup_time != null)
				return false;
		} else if (!pickup_time.equals(other.pickup_time))
			return false;
		if (quantity != other.quantity)
			return false;
		if (restaurant_id != other.restaurant_id)
			return false;
		if (restaurant_name == null) {
			if (other.restaurant_name != null)
				return false;
		} else if (!restaurant_name.equals(other.restaurant_name))
			return false;
		if (upload_picture == null) {
			if (other.upload_picture != null)
				return false;
		} else if (!upload_picture.equals(other.upload_picture))
			return false;
		return true;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getUpload_picture() {
		return upload_picture;
	}
	public void setUpload_picture(String upload_picture) {
		this.upload_picture = upload_picture;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPickup_location() {
		return pickup_location;
	}
	public void setPickup_location(String pickup_location) {
		this.pickup_location = pickup_location;
	}
	public Time getPickup_time() {
		return pickup_time;
	}
	public void setPickup_time(Time pickup_time) {
		this.pickup_time = pickup_time;
	}

	
	
	
	
}
