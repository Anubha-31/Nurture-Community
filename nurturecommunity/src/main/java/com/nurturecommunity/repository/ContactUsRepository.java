package com.nurturecommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nurturecommunity.Dao.ContactUsDetails;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUsDetails, Long>{

}
