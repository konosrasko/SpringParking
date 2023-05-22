//package com.example.parking.repository;
//
//import com.example.parking.entity.Parking;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class ParkingRepoTest {
//
//    @Autowired
//    private ParkingRepo underTest;
//
//    @AfterEach
//    void tearDown(){
//        underTest.deleteAll();
//    }
//    @Test
//    void itShouldCheckIfParkingExist() {
//        String test1 = "test1";
//        Parking parking = new Parking(1, test1);
//        underTest.save(parking);
//
//       Optional<Parking> optionalParking = underTest.findParkingByName(test1);
//
//       assertTrue(optionalParking.isPresent());
//    }
//
//}