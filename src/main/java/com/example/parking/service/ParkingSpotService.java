package com.example.parking.service;

import com.example.parking.dto.ParkingSpotDTO;

import java.util.List;

public interface ParkingSpotService {

    public List<ParkingSpotDTO> findAllParkingSpots();

    public ParkingSpotDTO findParkingSpotById(int id);

    public List<ParkingSpotDTO> findSpotsByZoneId(int zoneId);

    public ParkingSpotDTO createNewSpot(ParkingSpotDTO parkingSpotDTO, int zoneId);

}
