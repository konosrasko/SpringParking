package com.example.parking.service.impl;

import com.example.parking.dto.*;
import com.example.parking.entity.ParkingZone;
import com.example.parking.entity.PriceList;
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
import java.util.List;

import java.util.ArrayList;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
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

    @BeforeEach
    void beforeEach(){
        ParkingDTO parkingDTO =  ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);

        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO savedSpot = parkingService.addSpot(parkingSpotDTO, savedZone.getParkingZoneId());



    }

    @Test
    void addPriceList(){
        PriceListDTO priceListDTO = new PriceListDTO();
        PriceListDTO savedPriceList= priceListService.addPriceList(priceListDTO,1);



        assertNotNull(savedPriceList);
        System.out.println(savedPriceList.toString());
    }
    @Test
    void getPriceList(){

        PriceListDTO priceListDTO = new PriceListDTO();
        priceListService.addPriceList(priceListDTO,1);

        assertNotNull(priceListService.getPriceList(1));
        assertEquals(priceListService.getPriceList(1).size(),1);

    }
    @Test
    void addPriceScale(){
        PriceListDTO priceListDTO = new PriceListDTO();
        PriceListDTO savedPriceList= priceListService.addPriceList(priceListDTO,1);
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO();

        PriceScaleDTO savedPriceScale =priceListService.addPriceScales(1,priceScaleDTO);

        assertNotNull(savedPriceScale);
    }
    @Test
    void deleteScale(){
        PriceListDTO priceListDTO = new PriceListDTO();
        PriceListDTO savedPriceList= priceListService.addPriceList(priceListDTO,1);
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO();

        PriceScaleDTO savedPriceScale =priceListService.addPriceScales(1,priceScaleDTO);
        PriceScaleDTO deleted= priceListService.deleteScale(savedPriceScale.getPriceScaleId());

        System.out.println(savedPriceScale.toString());
        assertNull(savedPriceScale);
    }
    @Test
    void deletePriceList(){
        PriceListDTO priceListDTO = new PriceListDTO();
        PriceListDTO savedPriceList= priceListService.addPriceList(priceListDTO,1);
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO();

        PriceScaleDTO savedPriceScale =priceListService.addPriceScales(1,priceScaleDTO);

        PriceListDTO deleted = priceListService.deletePriceList(1);

        assertNull(deleted);
    }


}