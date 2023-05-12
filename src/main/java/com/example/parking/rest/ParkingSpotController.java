package com.example.parking.rest;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.service.ParkingSpotService;
import com.example.parking.service.ParkingSpotServiceImpl;
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
    public List<ParkingSpot> getParkingZones(){
        return parkingSpotServiceImpl.findAllParkingSpots();
    }

    @GetMapping("/parking-spot/{id}")
    public ParkingSpot getParkingSpotsById(@PathVariable int id){
        return parkingSpotServiceImpl.findParkingSpotById(id);
    }

    @GetMapping("/parking-zone/{zoneId}/parking-spots")
    public List<ParkingSpot> getParkingSpotsByParkingZone(@PathVariable("zoneId") int zoneId){
        return parkingSpotServiceImpl.findAllSpotsByParkingZone(zoneId);
    }

}
