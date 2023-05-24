package com.example.parking.repository;

import com.example.parking.entity.ParkingZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ParkingZoneRepoTest {
    @Autowired
    private ParkingZoneRepo parkingZoneRepo;

    @Test
    void saveTest() {
        ParkingZone parkingZone = new ParkingZone("type", "name");
        ParkingZone savedZone = parkingZoneRepo.save(parkingZone);
        assertAll(
                "Grouped assertions for parking zone",
                () -> assertEquals(parkingZone, savedZone, "entity should match return value"),
                () -> assertNotNull(savedZone, "saved value should not be null")
        );
    }

    @Test
    void shouldReturnTheSelectedSpot() {
        ParkingZone parkingZone1 = new ParkingZone("type", "name");
        ParkingZone parkingZone2 = new ParkingZone("type", "name");
        parkingZoneRepo.save(parkingZone1);
        parkingZoneRepo.save(parkingZone2);
        ParkingZone zone1 = parkingZoneRepo.findById(parkingZone1.getId()).get();
        assertAll(
                "Grouped assertions for parking zone",
                () -> assertEquals(parkingZoneRepo.findAll().size(), 2, "should save 2 values"),
                () -> assertNotNull(zone1, "should get if saved"),
                () -> assertEquals(parkingZone1, zone1, "should have matching values"),
                () -> assertNotEquals(parkingZone2, zone1, "should not have same values")
        );
    }
}
