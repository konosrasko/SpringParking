package com.example.parking.service;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingZoneService {

    ParkingSpotDTO findParkingSpotById(int id);

    List<ParkingSpotDTO> findSpotsByZoneId(int zoneId);

    void createNewSpot(ParkingSpotDTO parkingSpotDTO, int zoneId, int parkingId);

    void deleteSpot(int zoneId, int spotId);

}
