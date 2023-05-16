package com.example.parking.service.impl;

import com.example.parking.entity.Parking;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkingZoneServiceImplTest {

    @Mock
    private ParkingZoneRepo parkingZoneRepo;
    @Mock
    private ParkingSpotRepo parkingSpotRepo;
    private ParkingZoneServiceImpl parkingZoneServiceImpl;
    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        parkingZoneServiceImpl = new ParkingZoneServiceImpl(parkingZoneRepo,parkingSpotRepo);
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void findParkingZonesByParkingId() {
        Parking parking = new Parking(1,"name");
        parkingZoneServiceImpl.findParkingZonesByParkingId(parking.getId());
        //ArgumentCaptor<Integer> parkingId = ArgumentCaptor.forClass(Parking.class);
        verify(parkingZoneRepo).findByParkingId(parking.getId());
    }

    @Test
    void findParkingZoneById() {
    }

    @Test
    void saveParkingZone() {
    }

    @Test
    void deleteZone() {
    }
}