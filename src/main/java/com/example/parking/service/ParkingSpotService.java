package com.example.parking.service;

import com.example.parking.entity.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {

    public List<ParkingSpot> findAllParkingSpots();

    public ParkingSpot findParkingSpotById(int id);

    public List<ParkingSpot> findAllSpotsByParkingZone(int id);


}
