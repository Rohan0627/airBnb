package com.rohan.airBnb.Repository;

import com.rohan.airBnb.Entity.Guest;
import com.rohan.airBnb.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}
