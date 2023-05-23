package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ComponentScan(basePackages = {"com.example.parking"})
public class ParkingServiceImplTest {

    @Autowired
    private ParkingService parkingService;

    @Test
    void addParking() {
        List<ParkingZoneDTO> parkingZoneDTO = new ArrayList<>();
        ParkingDTO parkingDTO = new ParkingDTO("name",parkingZoneDTO);

        Parking parking = this.parkingService.addParking(parkingDTO);

        assertNotNull(parking);


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