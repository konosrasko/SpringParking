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

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public OffsetDateTime getOccupationDate() {
        return occupationDate;
    }

    public void setOccupationDate(OffsetDateTime occupationDate) {
        this.occupationDate = occupationDate;
    }

    public OffsetDateTime getVacancyDate() {
        return vacancyDate;
    }

    public void setVacancyDate(OffsetDateTime vacancyDate) {
        this.vacancyDate = vacancyDate;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
