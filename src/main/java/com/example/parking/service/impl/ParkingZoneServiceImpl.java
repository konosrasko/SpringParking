package com.example.parking.service.impl;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingService;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingZoneServiceImpl implements ParkingZoneService {

    private final ParkingZoneRepo parkingZoneRepo;

    private final ParkingSpotRepo parkingSpotRepo;

    @Autowired
    public ParkingZoneServiceImpl(ParkingZoneRepo parkingZoneRepo, ParkingSpotRepo parkingSpotRepo) {
        this.parkingZoneRepo = parkingZoneRepo;
        this.parkingSpotRepo = parkingSpotRepo;
    }

    @Override
    public List<ParkingZoneDTO> findParkingZonesByParkingId(int parkingId) {

        List<ParkingZone> parkingZones = this.parkingZoneRepo.findByParkingId(parkingId);
        return parkingZones
                .stream()
                .map(parkingZone -> new ParkingZoneDTO(
                        parkingZone.getId(),
                        parkingZone.getType()
                )).collect(Collectors.toList());
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
