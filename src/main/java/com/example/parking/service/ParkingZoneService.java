package com.example.parking.service;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingZoneService {
    private final ParkingZoneRepo parkingZoneRepo;

    @Autowired
    public ParkingZoneService(ParkingZoneRepo parkingZoneRepo) {
        this.parkingZoneRepo = parkingZoneRepo;
    }

    public List<ParkingZone> findAllParkingZones(){
        return parkingZoneRepo.findAll();
    }

    public ParkingZone findParkingZoneById(int id){
        Optional<ParkingZone> results = parkingZoneRepo.findById(id);

        ParkingZone parkingZone= null;

        if(results.isPresent()){
            parkingZone = results.get();
        }else{
            throw new ParkingException("There are no zones with the id: " + id);
        }

        return parkingZone;
    }

    public List<ParkingZoneDTO> findZonesByParking(Parking parking){
        List<ParkingZone> listOfParkingZone = this.parkingZoneRepo.findZonesByParking(parking);
        return new ArrayList<>();
    }


}
