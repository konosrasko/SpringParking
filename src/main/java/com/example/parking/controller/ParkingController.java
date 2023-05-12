package com.example.parking.controller;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.service.impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingController {
    private final ParkingServiceImpl parkingServiceImpl;

    @Autowired
    public ParkingController(ParkingServiceImpl parkingServiceImpl) {
        this.parkingServiceImpl = parkingServiceImpl;
    }

    @GetMapping("/parking")
    public List<ParkingDTO> getAllParkings(){
        return parkingServiceImpl.findAllParkings();
    }

    @GetMapping("/parking/{id}")
    public ParkingDTO getParkingById(@PathVariable int id){
        return parkingServiceImpl.findParkingById(id);
    }

    @PostMapping("/parking")
    public Parking addNewParking(@RequestBody ParkingDTO parkingDTO){
        return parkingServiceImpl.saveParking(parkingDTO);
    }
}
