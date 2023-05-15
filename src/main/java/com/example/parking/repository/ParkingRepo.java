package com.example.parking.repository;

import com.example.parking.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepo  extends JpaRepository<Parking, Integer> {

    @Query("SELECT p FROM Parking p WHERE p.name = ?1")
    Optional<Parking> findParkingByName(String name);



}
