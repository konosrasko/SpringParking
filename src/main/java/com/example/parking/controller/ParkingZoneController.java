package com.example.parking.controller;

import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingZoneController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/parking/{id}/parking-zones")
    public List<ParkingZoneDTO> getParkingZonesByParkingId(@PathVariable int id){
        return parkingService.getParkingZones(id);
    }

    @GetMapping("parking/{id}/parking-zones/{zoneId}")
    public ParkingZoneDTO getParkingZoneById(@PathVariable int parkingId, @PathVariable int zoneId){
        return parkingService.getParkingZoneById(parkingId,zoneId);
    }

    @PostMapping("/parking/{id}/parking-zones")
    public ParkingZoneDTO addNewParkingZone(@PathVariable int id, @RequestBody ParkingZoneDTO parkingZoneDTO){
        return new ParkingZoneDTO(parkingService.addZone(id,parkingZoneDTO));
    }

}
