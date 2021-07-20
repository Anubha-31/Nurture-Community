package com.nurturecommunity.repository;

import java.util.List;

import com.nurturecommunity.Dao.AddFoodDetails;
import com.nurturecommunity.Dao.AppUser;
import com.nurturecommunity.Dao.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
//    List<Profile> findByRestaurantNameContaining(String restaurant_name);
    List<Profile> findByid(int id);
}
