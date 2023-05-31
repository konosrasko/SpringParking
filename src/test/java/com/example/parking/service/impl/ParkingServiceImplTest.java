package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan(basePackages = {"com.example.parking"})
public class ParkingServiceImplTest {

    @Autowired
    private ParkingService parkingService;

    private ParkingDTO parkingDTO;
    private ParkingZoneDTO parkingZoneDTO;
    private ParkingSpotDTO parkingSpotDTO;
    private ParkingDTO savedParking;

    @BeforeEach
    void init(){
        parkingDTO = ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();

        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);

        savedParking = parkingService.addParking(parkingDTO);
    }

    @Test
    void addParking() {
        List<ParkingDTO> foundParking = parkingService.findAllParkings();

        assertNotNull(savedParking);
        assertTrue(foundParking.contains(savedParking));
    }

    @Test
    void findParkingById(){
        ParkingDTO parkingDTO1 = ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingDTO savedPark1 = parkingService.addParking(parkingDTO1);

        assertEquals(savedPark1, parkingService.findParkingById(savedPark1.getParkingId()));
    }

    @Test
    void findAllParking(){
        List<ParkingDTO> listOfParking = new ArrayList<>();
        listOfParking.add(savedParking);

        assertEquals(listOfParking.size() ,parkingService.findAllParkings().size());
    }

    @Test
    void addZone(){
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name1").type("type1").parkingSpotDTOList(new ArrayList<>()).build();

        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        assertNotNull(savedZone);
    }

    @Test
    void getParkingSpotsByZoneById(){
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        assertEquals(
                parkingService.findSpotsByZoneId(savedZone.getParkingZoneId()),
                parkingZoneDTO.getParkingSpotDTOList()
        );
    }

    @Test
    void getParkingZones(){
        assertEquals(parkingService.getParkingZones(savedParking.getParkingId()),
                savedParking.getParkingZoneDTOList());
    }

    @Test
    void addSpot(){
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        assertNotNull(savedSpot);
        assertEquals(savedSpot, parkingService.findParkingSpotById(savedSpot.getId()));
    }

    @Test
    void findParkingSpotById(){
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        assertNotNull(parkingService.findParkingSpotById(savedSpot.getId()));
    }

    @Test
    void findSpotsByZoneId(){
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        assertNotNull(parkingService.findSpotsByZoneId(savedZone.getParkingZoneId()));
        assertEquals(savedZone.getParkingSpotDTOList(), parkingService.findSpotsByZoneId(savedZone.getParkingZoneId()));
    }
}