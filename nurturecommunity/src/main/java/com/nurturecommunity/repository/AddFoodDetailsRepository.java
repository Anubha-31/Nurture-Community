package com.nurturecommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nurturecommunity.Dao.AddFoodDetails;


public interface AddFoodDetailsRepository extends JpaRepository<AddFoodDetails, Long> {

	List<AddFoodDetails> findByRestaurantNameContaining(String restaurantName);
	List<AddFoodDetails> findByRestaurantName(String restaurantName);
	List<AddFoodDetails> findByid(int id);
	
	
	
}
