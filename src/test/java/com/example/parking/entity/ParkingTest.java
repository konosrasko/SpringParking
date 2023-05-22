package com.example.parking.entity;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import org.junit.jupiter.api.Test;


import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {
    @Test
    void parkingEntityConstructionFromDTO(){
        ParkingDTO parkingDTO = new ParkingDTO("name");
        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        Parking parkingFromDTO = new Parking(parkingDTO);
        System.out.println(parkingFromDTO.toString());

        Parking parking = new Parking("name");
        ParkingZone parkingZone = new ParkingZone("type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        parkingZone.getParkingSpots().add(parkingSpot);
        parking.getParkingZones().add(parkingZone);
        System.out.println(parking.toString());

        assertThat(parkingFromDTO.equals(parking)).isTrue();
    }
}