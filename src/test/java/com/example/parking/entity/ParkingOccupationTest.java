package com.example.parking.entity;

import com.example.parking.dto.ParkingOccupationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingOccupationTest {
    @Test
    void DTOToEntity(){
        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO();
        ParkingOccupation parkingOccupation = new ParkingOccupation();
        assertEquals(parkingOccupation,new ParkingOccupation(new ParkingSpot(), parkingOccupationDTO));

    }
}