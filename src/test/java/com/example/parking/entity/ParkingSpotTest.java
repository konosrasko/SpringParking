package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingSpotTest {
    @Test
    void EntityToDTO(){
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        ParkingSpotDTO parkingSpotDTOfromEntity = new ParkingSpotDTO(parkingSpot);

        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);



        assertThat(parkingSpotDTO.equals(parkingSpotDTOfromEntity)).isTrue();

    }

    @Test
    void DTOtoEntity(){
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        ParkingSpot parkingSpotFromDTO = new ParkingSpot(parkingSpotDTO);

        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);

        assertThat(parkingSpot.equals(parkingSpotFromDTO)).isTrue();
    }


}