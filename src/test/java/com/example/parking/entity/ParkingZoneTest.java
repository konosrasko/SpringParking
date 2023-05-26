package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingZoneTest {
    @Test
    void zoneFromDTO(){
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);

        ParkingZone parkingZoneFromDTO = new ParkingZone(null, parkingZoneDTO);

        ParkingZone parkingZone = ParkingZone.builder().name("name").type("type").parkingSpots(new ArrayList<>()).build();
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        parkingZone.getParkingSpots().add(parkingSpot);

        assertThat(parkingZoneFromDTO.equals(parkingZone)).isTrue();

    }

    @Test
    void dtoFromEntity(){
        ParkingZone parkingZone = ParkingZone.builder().name("name").type("type").parkingSpots(new ArrayList<>()).build();
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        parkingZone.getParkingSpots().add(parkingSpot);

        ParkingZoneDTO parkingZoneDTOfromEntity = new ParkingZoneDTO(parkingZone);

        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);

        assertThat(parkingZoneDTO.equals(parkingZoneDTOfromEntity)).isTrue();
    }

}