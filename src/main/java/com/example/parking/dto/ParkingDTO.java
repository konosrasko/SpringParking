package com.example.parking.dto;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingDTO {
    private int parkingId;
    private String name;
    private List<ParkingZoneDTO> parkingZoneDTOList;
    private Parking parking;



    public ParkingDTO(){
        this.parkingZoneDTOList = new ArrayList<>();
    }

    public ParkingDTO(int parkingId, String name ) {
        this.parkingId = parkingId;
        this.name = name;
        this.parkingZoneDTOList = new ArrayList<>();
    }

    public ParkingDTO(Parking parking){
        this.parkingId = parking.getId();
        this.name = parking.getName();
        this.parkingZoneDTOList = parking.getParkingZones()
                .stream()
                .map(ParkingZoneDTO::new)
                .toList();
    }
    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParkingZoneDTO> getParkingZoneDTOList() {
        return parkingZoneDTOList;
    }

    public void setParkingZoneDTOList(List<ParkingZoneDTO> parkingZoneDTOList) {
        this.parkingZoneDTOList = parkingZoneDTOList;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
