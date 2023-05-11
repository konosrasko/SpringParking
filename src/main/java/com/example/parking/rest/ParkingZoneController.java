package com.example.parking.rest;

import com.example.parking.entity.ParkingZone;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingZoneController {
    private final ParkingZoneService parkingZoneService;

    @Autowired
    public ParkingZoneController(ParkingZoneService parkingZoneService) {
        this.parkingZoneService = parkingZoneService;
    }

    @GetMapping("/parking-zones")
    public List<ParkingZone> getParkingZones(){
        return parkingZoneService.findAllParkingZones();
    }

    @GetMapping("/parking-zone/{id}")
    public ParkingZone getParkingZoneById(@PathVariable int id){
        return parkingZoneService.findParkingZoneById(id);
    }


}
