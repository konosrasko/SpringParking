package com.example.parking.service;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.repository.ParkingRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ParkingService {

    List<ParkingDTO> findAllParkings();

    ParkingDTO findParkingById(int id);

    ParkingDTO addParking(ParkingDTO parkingDTO);

    Boolean findIfParkingExistById(int parkingId);

    ParkingZoneDTO addZone(int id, ParkingZoneDTO parkingZoneDTO);

    List<ParkingZoneDTO> getParkingZones(int id);

    ParkingZoneDTO getParkingZoneById(int parkingId, int zoneId);

    ParkingSpotDTO findParkingSpotById(int id);

    List<ParkingSpotDTO> findSpotsByZoneId(int zoneId);

    ParkingSpotDTO addSpot(ParkingSpotDTO parkingSpotDTO, int zoneId);

    void deleteSpot(int zoneId, int spotId);

}
