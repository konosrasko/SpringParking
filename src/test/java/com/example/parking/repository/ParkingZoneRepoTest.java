package com.example.parking.repository;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ParkingZoneRepoTest {

    @Autowired
    private ParkingZoneRepo parkingZoneRepo;
    @Autowired
    private ParkingRepo parkingRepo;
    @Test
    void findByParkingId() {
        Parking parking = new Parking(1,"test");
        ParkingZone parkingZone = new ParkingZone("type");
        parking.setParkingZones(new ArrayList<>());
        parking.getParkingZones().add(parkingZone);
        this.parkingRepo.save(parking);
        this.parkingZoneRepo.save(parkingZone);

        Parking p = parkingRepo.findById(parking.getId()).get();

        assertEquals(true,
                p.getParkingZones().contains(parkingZoneRepo.findByParkingId(p.getId()))
        );



    }
}