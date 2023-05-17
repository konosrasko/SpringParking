package com.example.parking.repository;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepo extends JpaRepository<ParkingSpot, Integer> {

    @Query("SELECT ps FROM ParkingSpot ps WHERE zone.Id = ?1")
    List<ParkingSpot> findSpotsByZoneId(int zoneId);

    @Query("SELECT CASE WHEN COUNT(sp) > 0 THEN TRUE ELSE FALSE END FROM ParkingSpot sp WHERE sp.zoneId = ?1")
    boolean checkIfZoneIdExists(int zoneId);

}
