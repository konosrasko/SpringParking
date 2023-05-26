package com.example.parking.repository;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.service.ParkingService;
import com.example.parking.service.impl.ParkingServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ParkingSpotRepoTest {

    @Autowired
    private ParkingSpotRepo parkingSpotRepo;

    @Test
    void shouldSaveNewSpotAndReturnIt() {

        //Arrange
        ParkingSpot parkingSpot = ParkingSpot.builder().name("name").type("type").occupied(false).build();

        //Act
        ParkingSpot savedSpot = parkingSpotRepo.save(parkingSpot);

        //Assert
        Assertions.assertThat(savedSpot).isNotNull();
        Assertions.assertThat(savedSpot.getId()).isGreaterThan(0);
        System.out.println(parkingSpotRepo.findAll());
    }

    @Test
    void shouldReturnAllSpots(){

        ParkingSpot parkingSpot1 = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        ParkingSpot parkingSpot2 = ParkingSpot.builder().name("name2").type("type2").occupied(false).build();

        parkingSpotRepo.save(parkingSpot1);
        parkingSpotRepo.save(parkingSpot2);

        List<ParkingSpot> savedSpotList = parkingSpotRepo.findAll();

        Assertions.assertThat(savedSpotList).isNotNull();
        Assertions.assertThat(savedSpotList.size()).isEqualTo(2);

    }

    @Test
    void shouldReturnTheSelectedSpot(){
        ParkingSpot parkingSpot1 = ParkingSpot.builder().name("name").type("type").occupied(false).build();

        parkingSpotRepo.save(parkingSpot1);

        ParkingSpot selectedSpot = parkingSpotRepo.findById(parkingSpot1.getId()).get();

        Assertions.assertThat(selectedSpot).isNotNull();
    }

    @Test
    void shouldUpdateSpot(){
        ParkingSpot parkingSpot1 = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        parkingSpotRepo.save(parkingSpot1);

        ParkingSpot savedSpot = parkingSpotRepo.findById(parkingSpot1.getId()).get();
        savedSpot.setName("spot_changed");
        savedSpot.setType("type_changed");
        savedSpot.setOccupied(true);

        ParkingSpot updatedSpot = parkingSpotRepo.save(savedSpot);

        Assertions.assertThat(updatedSpot.getName()).isNotNull();
        Assertions.assertThat(updatedSpot.getType()).isNotNull();
        Assertions.assertThat(updatedSpot.isOccupied()).isTrue();

    }

    @Test
    void shouldDeleteSpot(){
        ParkingSpot parkingSpot1 = ParkingSpot.builder().name("name").type("type").occupied(false).build();
        parkingSpotRepo.save(parkingSpot1);

        parkingSpotRepo.deleteById(parkingSpot1.getId());

        Optional<ParkingSpot> foundSpot = parkingSpotRepo.findById(parkingSpot1.getId());

        Assertions.assertThat(foundSpot).isEmpty();
    }


}
