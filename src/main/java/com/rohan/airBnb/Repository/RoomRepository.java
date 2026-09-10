package com.rohan.airBnb.Repository;

import com.rohan.airBnb.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}