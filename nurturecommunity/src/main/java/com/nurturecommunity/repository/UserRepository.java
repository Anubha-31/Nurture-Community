package com.nurturecommunity.repository;


import com.nurturecommunity.Dao.AppUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	
	List<AppUser> findByEmailaddress(String emailaddress);
	List<AppUser> findAllByusertype(String usertype);
	List<AppUser> findAllById(Long id);

	
}

