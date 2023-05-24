package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
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
        Parking parking = new Parking(parkingDTO);
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO parkingZone= parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        assertNotNull(parkingZone);

    }
    @Test
    void getParkingZones(){
        ParkingDTO parkingDTO = new ParkingDTO("ParkingName");
        Parking parking = new Parking(parkingDTO);
        ParkingDTO savedParking =parkingService.addParking(parkingDTO);


        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("ZoneName","ZoneName");
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        ParkingZoneDTO savedZone = parkingService.addZone(savedParking.getParkingId(), parkingZoneDTO);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO();
        //parkingService.addSpot(parkingSpotDTO, parkingZoneDTO.getParkingZoneId());


        assertEquals(parkingService.findSpotsByZoneId(savedZone.getParkingZoneId()),parkingZoneDTO.getParkingSpotDTOList());

    }



}


//    @Test
//    void canFindAllParkings() {
//        parkingService.findAllParkings();
//
//        verify(parkingRepo).findAll();
//
//    }
//
//    @Test
//    void findIfParkingExistById() {
//    ParkingDTO parkingDTO = new ParkingDTO(1,"dsaf");
//    Parking parking = null;
//
//    assertEquals(parkingService.addParking(parkingDTO),parking);
//    assertEquals(parkingService.findIfParkingExistById(0),false);
//
//    }
//
//    @Test
//    void canSaveParking() {
//        String test1 = "test1";
//        ParkingDTO parkingDTO = new ParkingDTO(1, test1);
//
//        parkingService.addParking(parkingDTO);
//
//        ArgumentCaptor<Parking> parkingArgumentCaptor = ArgumentCaptor
//                .forClass(Parking.class);
//
//        verify(parkingRepo)
//                .save(parkingArgumentCaptor.capture());
//        Parking capturedParking = parkingArgumentCaptor.getValue();
//
//        assertEquals(capturedParking.getName(),test1);
//
//
//    }
//
//
//}