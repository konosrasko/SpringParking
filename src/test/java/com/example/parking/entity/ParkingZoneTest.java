package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingZoneTest {
    @Test
    void entityToDTO(){
        ParkingZone parkingZone = new ParkingZone(1,"type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        parkingZone.getParkingSpots().add(parkingSpot);

        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO(parkingZone);

        assertEquals(parkingZoneDTO.getName(),parkingZone.getName());
    }

    @Test
    void DTOtoEntity(){
        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);

        ParkingZone parkingZone = new ParkingZone(parkingZoneDTO);

        assertEquals(parkingZone.getName(),parkingZoneDTO.getName());
    }

}