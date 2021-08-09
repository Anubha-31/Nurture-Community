package com.nurturecommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findBycustomeremail(String emailaddress);
}
