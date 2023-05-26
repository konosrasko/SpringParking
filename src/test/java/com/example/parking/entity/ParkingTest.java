package com.example.parking.entity;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingTest {

    @Test
    void parkingEntityConstructionFromDTO(){
        ParkingDTO parkingDTO =  ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        Parking parkingFromParkingDTO = new Parking(parkingDTO);

        Parking parking = Parking.builder().name("name").parkingZones(new ArrayList<>()).build();
        ParkingZone parkingZone = ParkingZone.builder().name("name").type("type").parkingSpots(new ArrayList<>()).build();
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        parkingZone.getParkingSpots().add(parkingSpot);
        parking.getParkingZones().add(parkingZone);

        assertThat(parkingFromParkingDTO.equals(parking)).isTrue();
    }
    @Test
    void parkingDTOMappedByEntity(){
        Parking parking = Parking.builder().name("name").parkingZones(new ArrayList<>()).build();
        ParkingZone parkingZone = ParkingZone.builder().name("name").type("type").parkingSpots(new ArrayList<>()).build();
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        parkingZone.getParkingSpots().add(parkingSpot);
        parking.getParkingZones().add(parkingZone);
        ParkingDTO parkingDTOfromParking = new ParkingDTO(parking);

        ParkingDTO parkingDTO =  ParkingDTO.builder().name("name").parkingZoneDTOList(new ArrayList<>()).build();
        ParkingZoneDTO parkingZoneDTO = ParkingZoneDTO.builder().name("name").type("type").parkingSpotDTOList(new ArrayList<>()).build();
        ParkingSpotDTO parkingSpotDTO = ParkingSpotDTO.builder().name("name").type("type").occupied(false).build();
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);

        assertThat(parkingDTOfromParking.equals(parkingDTO)).isTrue();
    }

    @Test
    void testTest(){
        Parking parking = Parking.builder().name("name").parkingZones(new ArrayList<>()).build();
        System.out.println(parking.toString());
    }
}
