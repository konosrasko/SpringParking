package com.example.parking.repository;

import com.example.parking.entity.ParkingOccupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingOccupationRepo extends JpaRepository<ParkingOccupation,Integer> {
}
