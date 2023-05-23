package com.example.parking.repository;

import com.example.parking.ParkingApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = ParkingApplication.class)
@ComponentScan(basePackages = "com.esewa")
@DataJpaTest
class ParkingRepoTest {

    @Autowired
    private ParkingRepo underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void itShouldCheckIfParkingExist() {

    }

}
