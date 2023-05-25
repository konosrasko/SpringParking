package com.example.parking.service.impl;

import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.entity.ParkingOccupation;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingOccupationRepo;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingOccupationService;
import com.example.parking.service.ParkingService;
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
    public List<ParkingOccupation> getParkingHistoryByParkingId(int parkingId) {
        return new ArrayList<>();
    }

    @Override
    public void saveParkingOccupation(int spotId, ParkingOccupationDTO parkingOccupationDTO) {
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
                parkingOccupationRepo.save(parkingOccupation);
            }
        }
    }

    @Override
    public ParkingOccupationDTO updateParkingOccupation(ParkingOccupation parkingOccupation) {
        return new ParkingOccupationDTO(parkingOccupation);
    }
}
