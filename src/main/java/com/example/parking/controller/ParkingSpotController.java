package com.example.parking.controller;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService parkingSpotServiceImpl;

    @GetMapping("/parking-spots")
    public List<ParkingSpotDTO> getParkingZones(){
        return parkingSpotServiceImpl.findAllParkingSpots();
    }

    @GetMapping("/parking-spot/{id}")
    public ParkingSpotDTO getParkingSpotsById(@PathVariable int id){
        return parkingSpotServiceImpl.findParkingSpotById(id);
    }

}