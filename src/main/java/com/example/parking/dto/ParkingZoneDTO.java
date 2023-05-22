package com.example.parking.dto;

import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDTO {
    private final List<ParkingSpotDTO> parkingSpotDTOList;
    private int parkingZoneId;
    private String type;
    private String name;

    public ParkingZoneDTO(String type, String name) {
        this.type = type;
        this.name = name;
        this.parkingSpotDTOList = new ArrayList<>();
    }

    public ParkingZoneDTO(int parkingZoneId, String type, String name) {
        this.parkingZoneId = parkingZoneId;
        this.type = type;
        this.name = name;
        this.parkingSpotDTOList = new ArrayList<>();
    }

    public ParkingZoneDTO(ParkingZone parkingZone){
        this.parkingZoneId = parkingZone.getId();
        this.type = parkingZone.getType();
        this.name = parkingZone.getName();
        this.parkingSpotDTOList = parkingZone.getParkingSpots()
                .stream()
                .map(ParkingSpotDTO::new)
                .toList();
    }


    public List<ParkingSpotDTO> getParkingSpotDTOList() {
        return parkingSpotDTOList;
    }

    public int getParkingZoneId() {
        return parkingZoneId;
    }

    public void setParkingZoneId(int parkingZoneId) {
        this.parkingZoneId = parkingZoneId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
