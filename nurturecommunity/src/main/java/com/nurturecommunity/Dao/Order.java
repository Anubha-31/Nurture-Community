package com.nurturecommunity.Dao;

import java.util.Arrays;
import java.util.Date;

import javax.mail.Multipart;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    private String customeremail;
    private int food_id;
    private int restaurant_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date(System.currentTimeMillis());

    private String restaurant_name;
	private String item_description;
	private String item_name;
	
	private byte[] picture;
	
	
    public Order(){

    }

	public Integer getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String customerEmail() {
		return customeremail;
	}

	public void setCustomerEmail(String customeremail) {
		this.customeremail = customeremail;
	}
	
	public Integer getFoodId() {
		return food_id;
	}

	public void setFoodId(int food_id) {
		this.food_id = food_id;
	}
	
	public Integer getRestaurantId() {
		return restaurant_id;
	}

	public void setRestaurantId(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Order(String customeremail) {
		super();
		this.customeremail = customeremail;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_email=" + customeremail + ", food_id=" + food_id + ", restaurant_id="
				+ restaurant_id + ", created_at=" + created_at + ", restaurant_name=" + restaurant_name
				+ ", item_description=" + item_description + ", item_name=" + item_name + ", picture="
				+ Arrays.toString(picture) + "]";
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	

}