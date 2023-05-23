package com.example.parking.entity;

import com.example.parking.ParkingApplication;
import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.service.impl.ParkingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@SpringBootTest(classes = ParkingApplication.class)
class ParkingTest {

    @Autowired
    private ParkingRepo parkingRepo;

    @Autowired
    private ParkingServiceImpl parkingService;
    @Test
    void parkingEntityConstructionFromDTO(){



        ParkingDTO parkingDTO = new ParkingDTO("name");
        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO("type","name");
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO("name","type",false);
        parkingZoneDTO.getParkingSpotDTOList().add(parkingSpotDTO);
        parkingDTO.getParkingZoneDTOList().add(parkingZoneDTO);


        Parking parking = new Parking();
        ParkingZone parkingZone = new ParkingZone(parkingZoneDTO);
        ParkingSpot parkingSpot = new ParkingSpot(parkingSpotDTO);
        parkingZone.getParkingSpots().add(parkingSpot);
        parking.getParkingZones().add(parkingZone);
        System.out.println(parking.toString());

        assertThat(parkingService.saveParking(parkingDTO).equals(parking)).isTrue();


    }
}
