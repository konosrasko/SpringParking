package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class ParkingZoneTest {
    @Test
    void zoneFromDTO(){
        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);

        ParkingZone parkingZoneFromDTO = new ParkingZone(parkingZoneDTO);

        ParkingZone parkingZone = new ParkingZone("type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        parkingZone.getParkingSpots().add(parkingSpot);

        assertThat(parkingZoneFromDTO.equals(parkingZone)).isTrue();

    }

    @Test
    void dtoFromEntity(){
        ParkingZone parkingZone = new ParkingZone("type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        parkingZone.getParkingSpots().add(parkingSpot);

        ParkingZoneDTO parkingZoneDTOfromEntity = new ParkingZoneDTO(parkingZone);

        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);

        assertThat(parkingZoneDTO.equals(parkingZoneDTOfromEntity)).isTrue();
    }

}