package com.example.parking.dto;

import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingDTO {
    private int parkingId;
    private String name;
    private List<ParkingZoneDTO> parkingZoneDTOList;



    public ParkingDTO(){
        this.parkingZoneDTOList = new ArrayList<>();
    }

    public ParkingDTO(int parkingId, String name ) {
        this.parkingId = parkingId;
        this.name = name;
        this.parkingZoneDTOList = new ArrayList<>();
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
}
