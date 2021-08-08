package com.nurturecommunity.Dao;

import java.util.Date;

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
    private String customer_email;
    private int food_id;
    private int restaurant_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at = new Date(System.currentTimeMillis());

    public Order(){

    }

	public Integer getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String customerEmail() {
		return customer_email;
	}

	public void setCustomerEmail(String customer_email) {
		this.customer_email = customer_email;
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

	public Order(String customer_email) {
		super();
		this.customer_email = customer_email;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_email=" + customer_email + "]";
	}

}