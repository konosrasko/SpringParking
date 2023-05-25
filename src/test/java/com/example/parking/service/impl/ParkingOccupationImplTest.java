package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.service.ParkingOccupationService;
import com.example.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan(basePackages = {"com.example.parking"})
class ParkingOccupationImplTest {
    @Autowired
    private ParkingOccupationService parkingOccupationService;
    @Autowired
    private ParkingService parkingService;

    @Test
    void saveParkingOccupation() {
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking = parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName", "ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("test", "test", false);
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO();
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);


        assertEquals(parkingOccupationDTO.getSpotId(),parkingService.findParkingSpotById(parkingOccupationDTO.getSpotId()).getId());
        assertNotNull(savedParkingOccupation);
        assertEquals(parkingService.findParkingSpotById(savedSpot.getId()).isOccupied(), true);


    }


}