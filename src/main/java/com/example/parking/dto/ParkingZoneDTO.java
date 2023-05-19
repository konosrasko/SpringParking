package com.example.parking.dto;

import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDTO {
    private int parkingZoneId;
    private String name;
    private String type;
    private List<ParkingSpotDTO> parkingSpotDTOList;

    public ParkingZoneDTO(){}

    public ParkingZoneDTO(ParkingZone parkingZone){
        this.parkingZoneId = parkingZone.getId();
        this.name = parkingZone.getName();
        this.type = parkingZone.getType();
        this.parkingSpotDTOList = parkingZone.getParkingSpots()
                .stream()
                .map(ParkingSpotDTO::new)
                .toList();
    }

    public List<ParkingSpotDTO> getParkingSpotDTOList() {
        if (parkingSpotDTOList == null){
            return new ArrayList<>();
        }
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
