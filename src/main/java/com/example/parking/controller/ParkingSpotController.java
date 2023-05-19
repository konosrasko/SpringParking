package com.example.parking.controller;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/parking-spots/{id}")
    public ParkingSpotDTO getParkingSpotsById(@PathVariable int id){
        return parkingSpotService.findParkingSpotById(id);
    }

    @GetMapping("/parking-zones/{zoneId}/parking-spots")
    public List<ParkingSpotDTO> getAllParkingSpotsByZoneId(@PathVariable int zoneId){
        return parkingSpotService.findSpotsByZoneId(zoneId);
    }

    @PostMapping("/parking/{parkingId}/parking-zones/{zoneId}/parking-spots")
    public String addNewSpot(@RequestBody ParkingSpotDTO newSpotDTO, @PathVariable int parkingId, @PathVariable int zoneId){
        newSpotDTO.setId(0);
        parkingSpotService.createNewSpot(newSpotDTO, zoneId, parkingId);
        return ("New Spot has been added in zone with id " + zoneId);
    }

//    @PutMapping("parking-zones/{zoneId}/parking-spots")
//    public String updateSpot(@RequestBody ParkingSpotDTO updatedSpotDTO, @PathVariable int zoneId){
//        parkingSpotService.createNewSpot(updatedSpotDTO, zoneId);
//        return ("Spot with id " + updatedSpotDTO.getId() + " has been updated!");
//    }

    @DeleteMapping("parking-zones/{zoneId}/parking-spots/{spotId}")
    public String deleteSpot(@PathVariable int zoneId, @PathVariable int spotId){
        parkingSpotService.deleteSpot(zoneId, spotId);
        return ("Spot with id " + spotId + " has been deleted!");
    }

}
