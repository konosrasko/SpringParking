package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingSpotTest {
    @Test
    void EntityToDTO(){
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        ParkingSpotDTO parkingSpotDTOfromEntity = new ParkingSpotDTO(parkingSpot);
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        assertThat(parkingSpotDTO.equals(parkingSpotDTOfromEntity)).isTrue();
    }

    @Test
    void DTOtoEntity(){
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        ParkingSpot parkingSpotFromDTO = new ParkingSpot(null,parkingSpotDTO);
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        assertThat(parkingSpot.equals(parkingSpotFromDTO)).isTrue();
    }
}