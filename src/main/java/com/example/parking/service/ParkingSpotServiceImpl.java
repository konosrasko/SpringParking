package com.example.parking.service;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingSpotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService{

    private final ParkingSpotRepo parkingSpotRepo;

    @Autowired
    public ParkingSpotServiceImpl(ParkingSpotRepo parkingSpotRepo) {
        this.parkingSpotRepo = parkingSpotRepo;
    }

    public List<ParkingSpot> findAllParkingSpots(){
        return parkingSpotRepo.findAll();
    }

    public ParkingSpot findParkingSpotById(int id){
        Optional<ParkingSpot> results = parkingSpotRepo.findById(id);

        ParkingSpot parkingSpot = null;

        if(results.isPresent()){
            parkingSpot = results.get();
        }else{
            throw new ParkingException("There are no spots with the id: " + id);
        }

        return parkingSpot;
    }

    public List<ParkingSpot> findAllSpotsByParkingZone(int id){
        ParkingZone parkingZone = new ParkingZone();
        parkingZone.setId(id);

        List<ParkingSpot> result = parkingSpotRepo.findSpotByParkingZone(parkingZone);

        if(result.isEmpty()){
            throw new ParkingException("There are no parking spots in the parking zone of id: " + id);
        }

        return parkingSpotRepo.findSpotByParkingZone(parkingZone);

    }
}
