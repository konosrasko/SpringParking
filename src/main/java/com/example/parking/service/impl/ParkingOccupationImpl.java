package com.example.parking.service.impl;

import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingOccupation;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingOccupationRepo;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingOccupationImpl implements ParkingOccupationService {
    @Autowired
    private ParkingRepo parkingRepo;
    @Autowired
    private ParkingZoneRepo parkingZoneRepo;
    @Autowired
    private ParkingSpotRepo parkingSpotRepo;
    @Autowired
    private ParkingOccupationRepo parkingOccupationRepo;
    @Override
    public List<ParkingOccupationDTO> getParkingHistoryByParkingId(int parkingId) {
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if(parking.isEmpty()) {
            throw new ParkingException("Parking does not exist");
        } else {
            return parking.get().getParkingZones()
                    .stream()
                    .flatMap(parkingZone ->  parkingZone.getParkingSpots()
                            .stream()
                            .flatMap(parkingSpot -> parkingOccupationRepo.findAll()
                                    .stream()
                                    .filter(parkingOccupation -> parkingOccupation.getParkingSpot().getId() == parkingSpot.getId())
                                    .map(ParkingOccupationDTO::new)
                            )
                    ).toList();
        }
    }

    @Override
    public ParkingOccupationDTO saveParkingOccupation(int spotId, ParkingOccupationDTO parkingOccupationDTO) {
        ParkingOccupation savedOccupation = new ParkingOccupation();
        Optional<ParkingSpot> parkingSpot = parkingSpotRepo.findById(spotId);
        if(parkingSpot.isEmpty()){
            throw new ParkingException("Parking spot does not exist"); //response error 404
        } else {
            if(parkingSpot.get().isOccupied()){
                throw new RuntimeException("Parking spot is occupied"); //response error 400
            } else {
                ParkingOccupation parkingOccupation = new ParkingOccupation(parkingOccupationDTO);
                parkingSpot.get().setOccupied(true);
                parkingOccupation.setParkingSpot(parkingSpotRepo.save(parkingSpot.get()));
                savedOccupation= parkingOccupationRepo.save(parkingOccupation);
                parkingOccupationDTO.setSpotId(spotId);
            }
        }
        return new ParkingOccupationDTO(savedOccupation);
    }

    @Override
    public ParkingOccupationDTO updateParkingOccupation(ParkingOccupation parkingOccupation) {
        return new ParkingOccupationDTO(parkingOccupation);
    }
}
