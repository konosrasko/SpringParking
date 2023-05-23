package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingSpotTest {
    @Test
    void EntityToDTO(){
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO(parkingSpot);

        assertThat(parkingSpot.getName().equals(parkingSpotDTO.getName())).isTrue();

    }

    @Test
    void DTOtoEntity(){
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        ParkingSpot parkingSpot = new ParkingSpot(parkingSpotDTO);

        assertThat(parkingSpotDTO.getName().equals(parkingSpot.getName())).isTrue();
    }


}