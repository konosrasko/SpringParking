package com.example.parking.entity;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingTest {

    @Test
    void parkingEntityConstructionFromDTO(){
        ParkingDTO parkingDTO = new ParkingDTO("name");
        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);
        Parking parkingFromParkingDTO = new Parking(parkingDTO);

        Parking parking = new Parking("name");
        ParkingZone parkingZone = new ParkingZone("type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        parkingZone.getParkingSpots().add(parkingSpot);
        parking.getParkingZones().add(parkingZone);

        assertThat(parkingFromParkingDTO.equals(parking)).isTrue();
    }
    @Test
    void parkingDTOmappedByEntity(){
        Parking parking = new Parking("name");
        ParkingZone parkingZone = new ParkingZone("type","name");
        ParkingSpot parkingSpot = new ParkingSpot("name","type",false);
        parkingZone.getParkingSpots().add(parkingSpot);
        parking.getParkingZones().add(parkingZone);
        ParkingDTO parkingDTOfromParking = new ParkingDTO(parking);

        ParkingDTO parkingDTO = new ParkingDTO("name");
        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);

        assertThat(parkingDTOfromParking.equals(parkingDTO)).isTrue();
    }
}
