package com.example.parking.service.impl;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.ParkingZone;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingService;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingZoneServiceImpl implements ParkingZoneService {
    @Autowired
    private final ParkingService parkingService;
    @Autowired
    private final ParkingZoneRepo parkingZoneRepo;
    @Autowired
    private final ParkingSpotRepo parkingSpotRepo;

    public ParkingZoneServiceImpl(ParkingService parkingService, ParkingZoneRepo parkingZoneRepo, ParkingSpotRepo parkingSpotRepo) {
        this.parkingService = parkingService;
        this.parkingZoneRepo = parkingZoneRepo;
        this.parkingSpotRepo = parkingSpotRepo;
    }

    @Override
    public List<ParkingZoneDTO> findParkingZonesByParkingId(int parkingId) {
        return parkingService.findParkingById(parkingId).getParkingZoneDTOList();
    }

    @Override
    public ParkingZoneDTO findParkingZoneByParkingId(int parkingId, int zoneIndex) {
        return null;
    }

    @Override
    public ParkingZoneDTO saveParkingZone(ParkingZone parkingZone) {
        return null;
    }

    @Override
    public void deleteZone(ParkingZone parkingZone) {

    }
}
