package com.example.parking.repository;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

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
        ParkingZone parkingZone = new ParkingZone(1,1,"type","name");
        parking.setParkingZones(new ArrayList<>());
        parking.getParkingZones().add(parkingZone);
        parkingRepo.save(parking);
        parkingZoneRepo.save(parkingZone);

        Parking p = parkingRepo.findById(parking.getId()).get();
        List<ParkingZone> pz = parkingZoneRepo.findByParkingId(p.getId());

        assertTrue(pz.equals(p.getParkingZones()));



    }
}