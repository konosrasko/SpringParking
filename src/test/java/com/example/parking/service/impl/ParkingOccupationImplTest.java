package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingOccupation;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import com.example.parking.service.ParkingOccupationService;
import com.example.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    void putParkingOccupation(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking = parkingService.addParking(parkingDTO);

        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName", "ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("test", "test", false);
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO();
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);

        System.out.println(savedParkingOccupation.getVacancyDate());

        assertNotNull(parkingOccupationService.updateParkingHistoryOccupation(1).getVacancyDate());
    }

    @Test
    void getParkingHistory(){
        Parking parking = new Parking("name");
        ParkingZone parkingZone = new ParkingZone("type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);

        parking.getParkingZones().add(parkingZone);
        parkingZone.getParkingSpots().add(parkingSpot);

        ParkingOccupation parkingOccupation = ParkingOccupation.builder()
                .occupationDate(null)
                .vacancyDate(null)
                .cost(0)
                .plate("plate")
                .build();

        parkingOccupation.setParkingSpot(parkingSpot);
        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO(parkingOccupation);

        parkingOccupationService.saveParkingOccupation(1, parkingOccupationDTO);

        assertEquals(parkingOccupationService.getParkingHistoryByParkingId(1).size(),1);
        assertTrue(parkingOccupationService.getParkingHistoryByParkingId(1).contains(parkingOccupationDTO));
    }
}