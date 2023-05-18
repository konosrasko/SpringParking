package com.example.parking.controller;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking")
    public List<ParkingDTO> getAllParking() {
        return parkingService.findAllParkings();
    }

    @GetMapping("/parking/{id}")
    public ParkingDTO getParkingById(@PathVariable int id) {
        return parkingService.findParkingById(id);
    }

    @PostMapping("/parking")
    public Parking addNewParking(@RequestBody ParkingDTO parkingDTO) {
        return parkingService.saveParking(parkingDTO);
    }
}
