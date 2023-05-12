package com.example.parking.service;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private final ParkingRepo parkingRepo;
    private final ParkingZoneRepo parkingZoneRepo;

    @Autowired
    public ParkingService(ParkingRepo parkingRepo, ParkingZoneRepo parkingZoneRepo) {
        this.parkingRepo = parkingRepo;
        this.parkingZoneRepo = parkingZoneRepo;
    }

    public List<ParkingDTO> findAllParkings() {

        return parkingRepo.findAll()
                .stream()
                .map(parking -> new ParkingDTO(
                        parking.getId(),
                        parking.getName()

                )).collect(Collectors.toList());
}

    public Parking findParkingById(int id){

        Optional<Parking> result = parkingRepo.findById(id);
        Parking parking = null;

        if(result.isPresent()){
            parking = result.get();
        }else{
            throw new ParkingException("There is no parking with id: " + id);
        }

        return parking;
    }



    public Parking saveParking(Parking parking){

        Optional<Parking> parkingByName = parkingRepo.findParkingByName(parking.getName());

        if(parkingByName.isPresent()){
            throw new ParkingException("Parking with name " + parking.getName() + " already exists!");
        }

        return parkingRepo.save(parking);
    }
}
