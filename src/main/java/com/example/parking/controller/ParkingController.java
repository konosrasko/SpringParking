package com.example.parking.controller;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
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
    public ParkingDTO addNewParking(@RequestBody ParkingDTO parkingDTO) {
        return parkingService.addParking(parkingDTO);
    }

    @GetMapping("/parking/{id}/parking-zones")
    public List<ParkingZoneDTO> getParkingZonesByParkingId(@PathVariable int id){
        return parkingService.getParkingZones(id);
    }

    @GetMapping("parking/{id}/parking-zones/{zoneId}")
    public ParkingZoneDTO getParkingZoneById(@PathVariable int id, @PathVariable int zoneId){
        return parkingService.getParkingZoneById(id,zoneId);
    }

    @PostMapping("/parking/{id}/parking-zones")
    public ParkingZoneDTO addNewParkingZone(@PathVariable int id, @RequestBody ParkingZoneDTO parkingZoneDTO){
        return (parkingService.addZone(id,parkingZoneDTO));
    }

    @GetMapping("/parking-spots/{id}")
    public ParkingSpotDTO getParkingSpotsById(@PathVariable int id){
        return parkingService.findParkingSpotById(id);
    }

    @GetMapping("/parking-zones/{zoneId}/parking-spots")
    public List<ParkingSpotDTO> getAllParkingSpotsByZoneId(@PathVariable int zoneId){
        return parkingService.findSpotsByZoneId(zoneId);
    }

    @PostMapping("/parking/{parkingId}/parking-zones/{zoneId}/parking-spots")
    public String addNewSpot(@RequestBody ParkingSpotDTO newSpotDTO, @PathVariable int parkingId, @PathVariable int zoneId){
        newSpotDTO.setId(0);
        parkingService.addSpot(newSpotDTO, zoneId);
        return ("New Spot has been added in zone with id " + zoneId);
    }

    @DeleteMapping("parking-zones/{zoneId}/parking-spots/{spotId}")
    public String deleteSpot(@PathVariable int zoneId, @PathVariable int spotId){
        parkingService.deleteSpot(zoneId, spotId);
        return ("Spot with id " + spotId + " has been deleted!");
    }

}
