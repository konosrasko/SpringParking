package com.example.parking.rest;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
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
        ParkingZone parkingZone = parkingZoneService.findParkingZoneById(id);

        if(parkingZone == null){
            throw new ParkingException("There is no parking zone with this id: " + id);
        }

        return parkingZone;
    }

    @GetMapping("/parking/{parkId}/parking-zones")
    public List<ParkingZone> getAllParkingSpotsByZone(@PathVariable("parkId") int parkId) {
        Parking parking = new Parking();
        parking.setId(parkId);

        if(parkingZoneService.getAllParkingZonesByParking(parking).isEmpty()){
            throw new ParkingException("There is no parking with id: " + parkId);
        }

        return parkingZoneService.getAllParkingZonesByParking(parking);
    }
    @PostMapping("/parking/{parkId}/parking-zones")
    public void addZone(@PathVariable("parkId") int parkId,@RequestBody ParkingZone zone){
        parkingZoneService.addZone(parkId,zone);
    }


}
