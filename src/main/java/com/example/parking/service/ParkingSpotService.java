package com.example.parking.service;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.entity.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {

    public List<ParkingSpotDTO> findAllParkingSpots();

    public ParkingSpotDTO findParkingSpotById(int id);


}
