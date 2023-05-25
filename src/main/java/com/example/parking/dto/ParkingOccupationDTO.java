package com.example.parking.dto;

import com.example.parking.entity.ParkingOccupation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ParkingOccupationDTO {
//    private int parkingId;
//    private int zoneId;
    private int spotId;
    private OffsetDateTime occupationDate;
    private OffsetDateTime vacancyDate;
    private float cost;
    private String plate;

    public ParkingOccupationDTO(ParkingOccupation parkingOccupation){
//        this.parkingId = parkingOccupation.getParkingSpot().getZone().getParking().getId();
//        this.zoneId = parkingOccupation.getParkingSpot().getZone().getId();
        this.spotId = parkingOccupation.getParkingSpot().getId();
        this.occupationDate = parkingOccupation.getOccupationDate();
        this.vacancyDate = parkingOccupation.getVacancyDate();
        this.cost = parkingOccupation.getCost();
        this.plate = parkingOccupation.getPlate();
    }
}
