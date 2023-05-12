package com.example.parking.repository;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepo extends JpaRepository<ParkingSpot, Integer> {

}
