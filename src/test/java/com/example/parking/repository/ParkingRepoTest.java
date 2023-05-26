package com.example.parking.repository;

import com.example.parking.ParkingApplication;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class ParkingRepoTest {
    @Autowired
    private ParkingRepo parkingRepo;

    @Test
    void saveTest() {
        Parking parking = Parking.builder().name("name").parkingZones(new ArrayList<>()).build();
        Parking savedParking = parkingRepo.save(parking);
        assertAll(
                "Grouped assertions for parking",
                () -> assertEquals(parking, savedParking, "entity should match return value"),
                () -> assertNotNull(savedParking, "saved value should not be null")
        );
    }
    @Test
    void getByIdTest() {
        Parking parking1 = Parking.builder().name("name").parkingZones(new ArrayList<>()).build();
        Parking parking2 = Parking.builder().name("name").parkingZones(new ArrayList<>()).build();
        parkingRepo.save(parking1);
        parkingRepo.save(parking2);
        Parking fetchedParking = parkingRepo.findById(parking1.getId()).get();
        assertAll(
                "Grouped assertions for parking",
                () -> assertEquals(parkingRepo.findAll().size(), 2, "should save 2 values"),
                () -> assertNotNull(fetchedParking, "should get if saved"),
                () -> assertEquals(fetchedParking, parking1, "should have matching values"),
                () -> assertNotEquals(fetchedParking, parking2, "should not have same values")
        );
    }
}
