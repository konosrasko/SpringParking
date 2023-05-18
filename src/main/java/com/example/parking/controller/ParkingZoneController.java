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
    @Autowired
    private ParkingService parkingService;

    public ParkingZoneController(ParkingZoneService parkingZoneService) {
        this.parkingZoneService = parkingZoneService;
    }

    @GetMapping("/parking/{id}/parking-zones")
    public List<ParkingZoneDTO> getParkingZonesByParkingId(@PathVariable int id){
        return parkingService.findParkingById(id).getParkingZoneDTOList();
        //return parkingZoneService.findParkingZonesByParkingId(id);
    }

    @GetMapping("parking/{id}/parking-zones/{zoneId}")
    public ParkingZoneDTO getParkingZoneById(@PathVariable int id, @PathVariable int zoneId){
        return parkingService.findParkingById(id).getParkingZoneDTOList()
                .stream()
                .filter(parkingZoneDTO -> parkingZoneDTO.getParkingZoneId() == zoneId)
                .findAny()
                .get();
    }

    @PostMapping("/parking/{id}/parking-zones")
    public ParkingZoneDTO addNewParkingZone(@PathVariable int id, @RequestBody ParkingZoneDTO parkingZoneDTO){
        return new ParkingZoneDTO(parkingService.addZone(id,parkingZoneDTO));
    }

}
