package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest {


    @Autowired
    private ParkingService parkingService;
    private ParkingRepo parkingRepo;
    private ParkingZoneRepo parkingZoneRepo;
    private ParkingSpotRepo parkingSpotRepo;


    @Before
    public void setup(){
         parkingRepo = mock(ParkingRepo.class);

         parkingService.setParkingRepo(parkingRepo);

    }

    @Test
    void addParking(){


        Parking parking = new Parking("name");

        ParkingDTO parkingDTO = new ParkingDTO(0,"name");

        when(parkingRepo.save(Mockito.any(Parking.class))).thenReturn(parking);



        Parking savedParking = parkingService.addParking(parkingDTO);

        System.out.println(savedParking.toString());
        assertThat(savedParking).isNotNull();
    }


    @Test
    void canFindAllParkings() {
        parkingService.findAllParkings();

        verify(parkingRepo).findAll();

    }

    @Test
    void findIfParkingExistById() {
    ParkingDTO parkingDTO = new ParkingDTO(1,"dsaf");
    Parking parking = null;

    assertEquals(parkingService.addParking(parkingDTO),parking);
    assertEquals(parkingService.findIfParkingExistById(0),false);

    }

    @Test
    void canSaveParking() {
        String test1 = "test1";
        ParkingDTO parkingDTO = new ParkingDTO(1, test1);

        parkingService.addParking(parkingDTO);

        ArgumentCaptor<Parking> parkingArgumentCaptor = ArgumentCaptor
                .forClass(Parking.class);

        verify(parkingRepo)
                .save(parkingArgumentCaptor.capture());
        Parking capturedParking = parkingArgumentCaptor.getValue();

        assertEquals(capturedParking.getName(),test1);


    }


}