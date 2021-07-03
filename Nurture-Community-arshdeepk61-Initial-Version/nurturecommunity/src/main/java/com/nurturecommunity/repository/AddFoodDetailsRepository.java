package com.nurturecommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nurturecommunity.Dao.AddFoodDetails;


public interface AddFoodDetailsRepository extends JpaRepository<AddFoodDetails, Long> {


	
	List<AddFoodDetails> findAll();
	
	
}
