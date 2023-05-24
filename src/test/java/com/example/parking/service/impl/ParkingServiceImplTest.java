package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan(basePackages = {"com.example.parking"})
public class ParkingServiceImplTest {

    @Autowired
    private ParkingService parkingService;

    @Test
    void addParking() {
        List<ParkingSpotDTO> parkingSpotDTO = new ArrayList<>();
        List<ParkingZoneDTO> parkingZoneDTO = new ArrayList<>();
        ParkingDTO parkingDTO = new ParkingDTO("name",parkingZoneDTO);

        ParkingDTO savedParking =parkingService.addParking(parkingDTO);

        assertNotNull(savedParking);

    }
    @Test
    void findParkingById(){
        List<ParkingZoneDTO> parkingZoneDTO = new ArrayList<>();
        List<ParkingZoneDTO> parkingZoneDTO1 = new ArrayList<>();

        ParkingDTO parkingDTO = new ParkingDTO("name",parkingZoneDTO);
        ParkingDTO parkingDTO1 = new ParkingDTO("name1",parkingZoneDTO1);

        parkingService.addParking(parkingDTO);
        parkingService.addParking(parkingDTO1);

        assertEquals(2,parkingService.findParkingById(2).getParkingId());
    }
    @Test
    void findAllParkings(){
        List<ParkingZoneDTO> parkingZoneDTO = new ArrayList<>();
        List<ParkingZoneDTO> parkingZoneDTO1 = new ArrayList<>();

        ParkingDTO parkingDTO = new ParkingDTO("name",parkingZoneDTO);
        ParkingDTO parkingDTO1 = new ParkingDTO("name1",parkingZoneDTO1);

        parkingService.addParking(parkingDTO);
        parkingService.addParking(parkingDTO1);

        assertEquals(2,parkingService.findAllParkings().size());
    }

    @Test
    void addZone(){

        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO parkingZone= parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        assertNotNull(parkingZone);

    }
    @Test
    void getParkingZoneById(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);
        parkingService.getParkingZoneById(savedParking.getParkingId(),savedZone.getParkingZoneId());


        assertEquals(parkingService.findSpotsByZoneId(savedZone.getParkingZoneId()),parkingZoneDTO.getParkingSpotDTOList());

    }
    @Test
    void getParkingZones(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);


        assertEquals(parkingService.getParkingZones(savedParking.getParkingId()),savedParking.getParkingZoneDTOList());
    }
    @Test
    void addSpot(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("test","test",false);
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        assertNotNull(savedSpot);
    }

    @Test
    void findParkingSpotById(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("test","test",false);
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        assertNotNull(parkingService.findParkingSpotById(savedSpot.getId()));

    }
    @Test
    void findSpotsByZoneId(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("test","test",false);

        assertNotNull(parkingService.findSpotsByZoneId(savedZone.getParkingZoneId()));
    }



}