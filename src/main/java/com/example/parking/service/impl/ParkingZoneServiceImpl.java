package com.example.parking.service.impl;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public ParkingZone saveParkingZone(ParkingZoneDTO parkingZoneDTO) {

        Optional<ParkingZone> parkingZoneType = parkingZoneRepo.findById(parkingZoneDTO.getParkingZoneId());


        if(parkingZoneType.isPresent()){
            throw new ParkingException("ParkingZone not valid");
        }
        ParkingZone parkingZone = new ParkingZone();
        parkingZone.setType(parkingZoneDTO.getType());
        parkingZone.setParkingId(parkingZoneDTO.getParkingId());
        return parkingZoneRepo.save(parkingZone);

    }

    @Override
    public void deleteZone(ParkingZone parkingZone) {

    }
}
