package com.example.parking.repository;

import com.example.parking.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepo  extends JpaRepository<Parking, Integer> {

}
