package com.example.parking.service;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingZoneService {
    private final ParkingZoneRepo parkingZoneRepo;

    private ParkingService parkingService;

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

    public List<ParkingZone> getAllParkingZonesByParking(Parking parking){
        return parkingZoneRepo.findAllByParking(parking);
    }

    public void addZone(int parkId,ParkingZone parkingZone){
        Parking parking = parkingService.findParkingById(parkId);
        parkingZone.setParking(parking);
        parkingZoneRepo.save(parkingZone);
    }


    public ParkingService getParkingService() {
        return parkingService;
    }

    @Autowired
    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
}
