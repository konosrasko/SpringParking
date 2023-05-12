package com.example.parking.service;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ParkingZoneService {
    List<ParkingZoneDTO> findParkingZonesByParkingId(int parkingId);
    ParkingZoneDTO findParkingZoneByParkingId(int parkingId,int zoneIndex);
    ParkingZoneDTO saveParkingZone(ParkingZone parkingZone);
    void deleteZone(ParkingZone parkingZone);


}
