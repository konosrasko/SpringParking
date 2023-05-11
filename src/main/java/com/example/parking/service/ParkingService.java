package com.example.parking.service;

import com.example.parking.entity.Parking;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    private final ParkingRepo parkingRepo;

    @Autowired
    public ParkingService(ParkingRepo parkingRepo) {
        this.parkingRepo = parkingRepo;
    }

    public List<Parking> findAllParkings() {
        return parkingRepo.findAll();
    }

    public Parking findParkingById(int id){
        Optional<Parking> result = parkingRepo.findById(id);

        Parking theParking = null;

        if(result.isPresent()){
            theParking = result.get();
        }else{
            throw new ParkingException("There is no parking with id: " + id);
        }

        return theParking;
    }

    public Parking saveParking(Parking parking){
        Optional<Parking> parkingByName = parkingRepo.findParkingByName(parking.getName());

        if(parkingByName.isPresent()){
            throw new ParkingException("Parking with name " + parking.getName() + " already exists!");
        }

        return parkingRepo.save(parking);
    }
}
