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
    void getParkingOccupation(){
//        Parking parking = new Parking("name");
//        ParkingZone parkingZone = new ParkingZone("name","type");
//        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
//        parking.getParkingZones().add(parkingZone);
//        parkingZone.getParkingSpots().add(parkingSpot);
//
//
//        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO(1,null,null,0,"plate");
//        ParkingOccupation parkingOccupation = new ParkingOccupation(parkingOccupationDTO);
//        ParkingDTO parkingDTO = new ParkingDTO(parking);
//
//
//
//        parkingOccupationService.saveParkingOccupation(1,parkingOccupationDTO);
//
//
//        parkingService.addParking(parkingDTO);
//        List<ParkingOccupationDTO> savedParkingOccupation = parkingOccupationService.getParkingHistoryByParkingId(parking.getId());
//        System.out.println(savedParkingOccupation);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",true);
        List<ParkingSpotDTO>parkingSpotDTOList = new ArrayList<>();
        parkingSpotDTOList.add(parkingSpotDTO);

        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("name","type");
        parkingZoneDTO.setParkingSpotDTOList(parkingSpotDTOList);
        List<ParkingZoneDTO> parkingZoneDTOs = new ArrayList<>();
        parkingZoneDTOs.add(parkingZoneDTO);


        ParkingDTO parkingDTO = new ParkingDTO("ParkingName",parkingZoneDTOs);
        ParkingDTO savedParking = parkingService.addParking(parkingDTO);
        ParkingZoneDTO savedZone= parkingService.addZone(savedParking.getParkingId(),parkingZoneDTO);
        parkingService.addSpot(parkingSpotDTO,savedZone.getParkingZoneId());

        savedParking.setParkingZoneDTOList(parkingZoneDTOs);


        assertNotNull(parkingOccupationService.getParkingHistoryByParkingId(savedParking.getParkingId()));
        //assertTrue(parkingOccupationService.getParkingHistoryByParkingId(1).equals(savedParkingOccupation));

    }


}