package com.example.parking.rest;

import com.example.parking.entity.Parking;
import com.example.parking.exception.ParkingException;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingController {
    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/parking")
    public List<Parking> getAllParkings(){
        return parkingService.findAllParkings();
    }

    @GetMapping("/parking/{id}")
    public Parking getParkingById(@PathVariable int id){
        Parking parking = parkingService.findParkingById(id);

        if(parking == null){
            throw new ParkingException("There is no parking with id: " + id);
        }

        return parking;
    }

    @PostMapping("/parking")
    public Parking addNewParking(@RequestBody Parking parking){
        parking.setId(0);

        Parking newParking = parkingService.saveParking(parking);

        return newParking;
    }
}
