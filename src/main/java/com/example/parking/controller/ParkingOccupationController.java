package com.example.parking.controller;

import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.service.ParkingOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingOccupationController {
    @Autowired
    private ParkingOccupationService parkingOccupation;

    @PostMapping("/parking-spots/{spotId}")
    public String parkingAction(@PathVariable int spotId, @RequestBody ParkingOccupationDTO parkingOccupationDTO){
        parkingOccupation.saveParkingOccupation(spotId,parkingOccupationDTO);
        return "Parked";
    }

    @GetMapping("/parking/{parkingId}/history")
    public List<ParkingOccupationDTO> getParkingHistory(@PathVariable int parkingId){
        return parkingOccupation.getParkingHistoryByParkingId(parkingId);
    }

    @PutMapping("/parking-spots/{spotId}")
    public ParkingOccupationDTO updateParkingHistory(@PathVariable int spotId){
        return parkingOccupation.updateParkingHistoryOccupation(spotId);
    }
}
