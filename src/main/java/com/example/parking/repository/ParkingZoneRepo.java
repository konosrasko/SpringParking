package com.example.parking.repository;

import com.example.parking.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingZoneRepo extends JpaRepository<ParkingZone, Integer> {

}
