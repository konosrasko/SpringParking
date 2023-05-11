package com.example.parking.rest;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
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
    private final ParkingSpotService parkingSpotService;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping("/parking-spots")
    public List<ParkingSpot> getParkingZones(){
        return parkingSpotService.findAllParkingSpots();
    }

    @GetMapping("/parking-spot/{id}")
    public ParkingSpot getParkingSpotsById(@PathVariable int id){
        return parkingSpotService.findParkingSpotById(id);
    }

    @GetMapping("/parking-zone/{zoneId}/parking-spots")
    public List<ParkingSpot> getParkingSpotsByParkingZone(@PathVariable("zoneId") int zoneId){
        return parkingSpotService.findAllSpotsByParkingZone(zoneId);
    }

}
