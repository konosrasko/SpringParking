package com.example.parking.dto;

import com.example.parking.entity.ParkingOccupation;
import com.example.parking.entity.ParkingSpot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingOccupationDTOTest {
    @Test
    void entityToDTO(){
        ParkingOccupationDTO parkingOccupationDTO = new ParkingOccupationDTO();
        ParkingOccupation parkingOccupation = ParkingOccupation.builder().parkingSpot(new ParkingSpot()).build();
        assertEquals(parkingOccupationDTO,new ParkingOccupationDTO(parkingOccupation));
    }
}