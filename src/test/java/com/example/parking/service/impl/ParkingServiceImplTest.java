package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.repository.ParkingRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest {

    @Mock
    private ParkingRepo parkingRepo;
    private ParkingServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ParkingServiceImpl(parkingRepo);
    }


    @Test
    void canFindAllParkings() {
        underTest.findAllParkings();

        verify(parkingRepo).findAll();

    }

    @Test
    void findIfParkingExistById() {
        String test1 = "test1";
        Parking parking = new Parking(1,test1);
        assertEquals(parking.getName(),test1);
    }

    @Test
    void canSaveParking() {
        String test1 = "test1";
        ParkingDTO parkingDTO = new ParkingDTO(1, test1);

        underTest.saveParking(parkingDTO);

        ArgumentCaptor<Parking> parkingArgumentCaptor = ArgumentCaptor
                .forClass(Parking.class);

        verify(parkingRepo)
                .save(parkingArgumentCaptor.capture());
        Parking capturedParking = parkingArgumentCaptor.getValue();

        assertEquals(capturedParking.getName(),test1);


    }


}