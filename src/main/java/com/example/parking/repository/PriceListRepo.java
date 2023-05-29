package com.example.parking.repository;

import com.example.parking.entity.ParkingZone;
import com.example.parking.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepo extends JpaRepository<PriceList, Integer> {
}
