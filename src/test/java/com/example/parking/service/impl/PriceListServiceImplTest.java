package com.example.parking.service.impl;

import com.example.parking.dto.*;
import com.example.parking.repository.PriceListRepo;
import com.example.parking.service.ParkingService;
import com.example.parking.service.PriceListService;
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
class PriceListServiceImplTest {
    @Autowired
    private PriceListService priceListService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private PriceListRepo priceListRepo;

    private ParkingDTO savedParking;
    private ParkingZoneDTO savedZone;
    private ParkingSpotDTO savedSpot;
    private PriceListDTO savedList;
    private PriceScaleDTO savedScale;

    @BeforeEach
    void init(){
        ParkingDTO parkingDTO = ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        savedParking = parkingService.addParking(parkingDTO);

        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());

        PriceListDTO priceListDTO = PriceListDTO.builder().type("type").dateEnd(null).priceScaleDTOList(new ArrayList<>()).dateStart(null).build();
        savedList = priceListService.addPriceList(priceListDTO, savedZone.getParkingZoneId());

        PriceScaleDTO priceScaleDTO = PriceScaleDTO.builder().scaleDuration(120).scaleCost(1).scalePerTimeUnit(30).build();
        savedScale = priceListService.addPriceScales(savedList.getPriceListId(), priceScaleDTO);
    }

    @Test
    void addPriceList(){
        PriceListDTO foundList = new PriceListDTO(priceListRepo.findById(savedList.getPriceListId()).get());
        assertEquals(savedList, foundList);
    }

    @Test
    void getPriceList(){
        PriceListDTO priceListDTO1 = PriceListDTO.builder().type("type1").dateEnd(null).priceScaleDTOList(new ArrayList<>()).dateStart(null).build();
        priceListService.addPriceList(priceListDTO1, savedZone.getParkingZoneId());

        List<PriceListDTO> foundListsOfZone = priceListService.getPriceList(savedZone.getParkingZoneId());
        assertEquals(foundListsOfZone.size(), 2);
    }

    @Test
    void addPriceScale() {
        assertNotNull(savedScale);
    }

    @Test
    void deleteScale(){
        PriceScaleDTO deleted = priceListService.deleteScale(savedScale.getPriceScaleId());
        assertNull(deleted);
    }

    @Test
    void deletePriceList(){
        PriceListDTO deleted = priceListService.deletePriceList(savedList.getPriceListId());
        assertNull(deleted);
    }
}