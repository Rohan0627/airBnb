package com.rohan.airBnb.Repository;

import com.rohan.airBnb.Entity.Hotel;
import com.rohan.airBnb.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}