package com.example.parking.service.impl;

import com.example.parking.dto.*;
import com.example.parking.entity.ParkingOccupation;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.service.ParkingOccupationService;
import com.example.parking.service.ParkingService;
import com.example.parking.service.PriceListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

    @Test
    void saveParkingOccupation() {
        ParkingDTO parkingDTO =  ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingDTO savedParking = parkingService.addParking(parkingDTO);

        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO();
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);

        assertEquals(parkingOccupationDTO.getSpotId(),parkingService.findParkingSpotById(parkingOccupationDTO.getSpotId()).getId());
        assertNotNull(savedParkingOccupation);
        assertTrue(parkingService.findParkingSpotById(savedSpot.getId()).isOccupied());
    }

    @Test
    void putParkingOccupation(){
        ParkingDTO parkingDTO =  ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingDTO savedParking = parkingService.addParking(parkingDTO);

        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        List<PriceScaleDTO> priceScaleDTOList = new ArrayList<>();
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO(1,120,30,1);
        priceScaleDTOList.add(priceScaleDTO);
        PriceListDTO priceListDTO = new PriceListDTO(1,OffsetDateTime.now(),OffsetDateTime.now().plusHours(1),"type",priceScaleDTOList);
        priceListService.addPriceList(priceListDTO,1);


        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO(savedSpot.getId(),OffsetDateTime.now().minusHours(1),null,0,"yes");
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);
        ParkingOccupationDTO unParked =parkingOccupationService.updateParkingHistoryOccupation(1);

       System.out.println(unParked.getVacancyDate());

        assertNotNull(parkingOccupationService.updateParkingHistoryOccupation(savedSpot.getId()).getVacancyDate());


    }

    @Test
    void getParkingHistory(){
        ParkingDTO parking = ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingZoneDTO parkingZone = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();

        parkingZone.getParkingSpotDTOList().add(parkingSpotDTO);
        parking.getParkingZoneDTOList().add(parkingZone);

        parkingService.addParking(parking);

        ParkingOccupation parkingOccupation = ParkingOccupation.builder()
                .occupationDate(null)
                .vacancyDate(null)
                .cost(0)
                .plate("plate")
                .parkingSpot(parkingSpot)
                .build();

        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO(parkingOccupation);

        parkingOccupationService.saveParkingOccupation(1, parkingOccupationDTO);

        assertEquals(parkingOccupationService.getParkingHistoryByParkingId(1).size(),1);
        assertTrue(parkingOccupationService.getParkingHistoryByParkingId(1).contains(parkingOccupationDTO));
    }

    @Test
    void calcCost(){
        ParkingDTO parkingDTO =  ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingDTO savedParking = parkingService.addParking(parkingDTO);

        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        List<PriceScaleDTO> priceScaleDTOList = new ArrayList<>();
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO(1,120,30,1);
        priceScaleDTOList.add(priceScaleDTO);
        PriceListDTO priceListDTO = new PriceListDTO(1,OffsetDateTime.now(),OffsetDateTime.now().plusHours(1),"type",priceScaleDTOList);
        priceListService.addPriceList(priceListDTO,1);


        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO(savedSpot.getId(),OffsetDateTime.now().minusHours(1),null,0,"yes");
        ParkingOccupationDTO savedParkingOccupation = parkingOccupationService.saveParkingOccupation(savedSpot.getId(), parkingOccupationDTO);
        ParkingOccupationDTO unParked =parkingOccupationService.updateParkingHistoryOccupation(1);

        System.out.println(unParked.getVacancyDate());
        assertEquals(2,unParked.getCost());
    }

}