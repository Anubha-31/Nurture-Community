package com.nurturecommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nurturecommunity.Dao.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

}
