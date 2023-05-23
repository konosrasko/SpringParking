package com.example.parking.service;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;

import java.util.List;

public interface ParkingService {
    List<ParkingDTO> findAllParkings();

    ParkingDTO findParkingById(int id);

    Parking addParking(ParkingDTO parkingDTO);

    Boolean findIfParkingExistById(int parkingId);

    ParkingZone addZone(int id, ParkingZoneDTO parkingZoneDTO);

    List<ParkingZoneDTO> getParkingZones(int id);

    ParkingZoneDTO getParkingZoneById(int parkingId, int zoneId);

    ParkingSpotDTO findParkingSpotById(int id);

    List<ParkingSpotDTO> findSpotsByZoneId(int zoneId);

    void addSpot(ParkingSpotDTO parkingSpotDTO, int zoneId, int parkingId);

    void deleteSpot(int zoneId, int spotId);

}
