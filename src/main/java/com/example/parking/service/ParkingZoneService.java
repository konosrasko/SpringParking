package com.example.parking.service;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.ParkingZone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingZoneService {
    List<ParkingZoneDTO> findParkingZonesByParkingId(int parkingId);
    ParkingZoneDTO findParkingZoneById(int zoneId);
    ParkingZone saveParkingZone(ParkingZoneDTO parkingZoneDTO);
    void deleteZone(ParkingZone parkingZone);


}
