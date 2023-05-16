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
    private ParkingSpotService parkingSpotService;

    @GetMapping("/parking-spots")
    public List<ParkingSpotDTO> getParkingZones(){
        return parkingSpotService.findAllParkingSpots();
    }

    @GetMapping("/parking-spot/{id}")
    public ParkingSpotDTO getParkingSpotsById(@PathVariable int id){
        return parkingSpotService.findParkingSpotById(id);
    }

    @GetMapping("/parking-zone/{zoneId}/parking-spot")
    public List<ParkingSpotDTO> getAllParkingSpotsByZoneId(@PathVariable int zoneId){
        return parkingSpotService.findSpotsByZoneId(zoneId);
    }

}
