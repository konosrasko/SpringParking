/*
package com.example.parking.service.impl;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.repository.ParkingSpotRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkingSpotServiceImplTest {

    @Mock
    private ParkingSpotRepo parkingSpotRepo;
    private ParkingSpotServiceImpl testingSpotService;

    @Test
    void canFindAllSpots(){
        ParkingSpot parkingSpot = new ParkingSpot();
        testingSpotService.findAllParkingSpots();

        verify(parkingSpotRepo).findAll();

        assertEquals(parkingSpot.getName(),null);
    }


}*/
