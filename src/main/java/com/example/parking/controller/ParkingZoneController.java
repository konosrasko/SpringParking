package com.example.parking.controller;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.service.ParkingService;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingZoneController {
    @Autowired
    private final ParkingZoneService parkingZoneService;

    public ParkingZoneController(ParkingZoneService parkingZoneService) {
        this.parkingZoneService = parkingZoneService;
    }

    @GetMapping("/parking-zones")
    public List<ParkingZoneDTO> getParkingZones(){
        return null;
    }

    @GetMapping("/parking-zones/{id}")
    public List<ParkingZoneDTO> getParkingZoneById(@PathVariable int id){
        return parkingZoneService.findParkingZonesByParkingId(id);
    }

    @PostMapping("/Parking/Zone")
    public ParkingZone addNewParkingZone(@RequestBody ParkingZoneDTO parkingZoneDTO){
        return parkingZoneService.saveParkingZone(parkingZoneDTO);
    }

}
