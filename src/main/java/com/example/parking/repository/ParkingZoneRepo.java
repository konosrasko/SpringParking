package com.example.parking.repository;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingZoneRepo extends JpaRepository<ParkingZone, Integer> {

    @Query("SELECT pz FROM ParkingZone pz WHERE pz.parkingId = ?1")
    List<ParkingZone> findByParkingId(int parkingId);
}
