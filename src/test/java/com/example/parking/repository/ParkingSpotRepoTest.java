package com.example.parking.repository;

import com.example.parking.entity.ParkingSpot;
import com.example.parking.service.impl.ParkingSpotServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParkingSpotRepoTest {

    @Mock
    private ParkingSpotRepo testSpotRepo;
    private ParkingSpotServiceImpl testSpotService;

    @Test
    void shouldSaveNewSpot() {
        ParkingSpot parkingSpot1 = new ParkingSpot(1,1, "spot_one", "normal", false);

        testSpotRepo.save(parkingSpot1);

        ArgumentCaptor<ParkingSpot> parkingSpotArgumentCaptor = ArgumentCaptor.forClass(ParkingSpot.class);
        verify(testSpotRepo).save(parkingSpotArgumentCaptor.capture());

        ParkingSpot captureSpot = parkingSpotArgumentCaptor.getValue();

        assertThat(captureSpot).isEqualTo(parkingSpot1);

    }

}