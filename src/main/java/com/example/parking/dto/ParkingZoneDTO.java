package com.example.parking.dto;

import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDTO {
    private List<ParkingSpotDTO> parkingSpotDTOList;
    private int parkingZoneId;
    private String type;
    private String name;

    public ParkingZoneDTO(){}

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

    public void setParkingSpotDTOList(List<ParkingSpotDTO> parkingSpotDTOList) {
        this.parkingSpotDTOList = parkingSpotDTOList;
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
