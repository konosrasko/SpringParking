package com.example.parking.service.impl;

import com.example.parking.dto.*;
import com.example.parking.service.ParkingOccupationService;
import com.example.parking.service.ParkingService;
import com.example.parking.service.PriceListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan(basePackages = {"com.example.parking"})
class ParkingOccupationImplTest {
    @Autowired
    private ParkingOccupationService parkingOccupationService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private PriceListService priceListService;

    private ParkingDTO savedParking;
    private ParkingZoneDTO savedZone;
    private ParkingSpotDTO savedSpot;
    private PriceListDTO savedList;
    private PriceScaleDTO savedScale;
    private ParkingDTO parkingDTO;
    private PriceListDTO priceListDTO;

    @BeforeEach
    void init(){
        parkingDTO = ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();

        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);

        priceListDTO = PriceListDTO.builder().type("type").dateEnd(null).priceScaleDTOList(new ArrayList<>()).dateStart(null).build();
        PriceScaleDTO priceScaleDTO = PriceScaleDTO.builder().scaleDuration(120).scaleCost(1).scalePerTimeUnit(30).build();

        priceListDTO.getPriceScaleDTOList().add(priceScaleDTO);

        savedParking = parkingService.addParking(parkingDTO);
        savedZone = savedParking.getParkingZoneDTOList().get(0);
        savedSpot = savedZone.getParkingSpotDTOList().get(0);

        savedList = priceListService.addPriceList(priceListDTO, savedZone.getParkingZoneId());
        savedScale = savedList.getPriceScaleDTOList().get(0);
    }

    @Test
    void saveParkingOccupation() {
        ParkingOccupationDTO parkingOccupationDTO = ParkingOccupationDTO.builder().plate("abc 123").occupationDate(OffsetDateTime.now()).build();
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);

        assertEquals(parkingOccupationDTO.getSpotId(),parkingService.findParkingSpotById(parkingOccupationDTO.getSpotId()).getId());
        assertNotNull(savedParkingOccupation);
        assertTrue(parkingService.findParkingSpotById(savedSpot.getId()).isOccupied());
    }

    @Test
    void putParkingOccupation(){
        System.out.println(savedZone.getParkingZoneId());
        ParkingOccupationDTO parkingOccupationDTO = ParkingOccupationDTO.builder().plate("abc 123").occupationDate(OffsetDateTime.now()).build();
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);

        assertNotNull(parkingOccupationService.updateParkingHistoryOccupation(savedSpot.getId()).getVacancyDate());
    }

    @Test
    void getParkingHistory(){
        System.out.println(savedZone.getParkingZoneId());
        ParkingOccupationDTO parkingOccupationDTO = ParkingOccupationDTO.builder().plate("abc 123").occupationDate(OffsetDateTime.now()).build();
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);
        //System.out.println(savedParking);
        assertEquals(1, parkingOccupationService.getParkingHistoryByParkingId(savedParking.getParkingId()).size());
        assertTrue(parkingOccupationService.getParkingHistoryByParkingId(savedParking.getParkingId()).contains(parkingOccupationDTO));
    }

    @Test
    void calcCost(){
        System.out.println(savedZone.getParkingZoneId());
        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO(savedSpot.getId(),OffsetDateTime.now().minusHours(1),null,0,"yes");
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);
        ParkingOccupationDTO unParked = parkingOccupationService.updateParkingHistoryOccupation(savedSpot.getId());

        System.out.println(unParked.getVacancyDate());
        assertEquals(2,unParked.getCost());
    }
}