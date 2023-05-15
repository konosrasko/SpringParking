package com.example.parking.dto;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDTO {
    private List<ParkingSpotDTO> parkingSpotDTOList;
    private int parkingZoneId;
    private String type;
    private String name;
    private int parkingId;

    public ParkingZoneDTO(){}

    public ParkingZoneDTO(int id, String type,List<ParkingSpotDTO> parkingSpotDTOList) {
        this.parkingZoneId = id;
        this.type = type;
        this.parkingSpotDTOList = parkingSpotDTOList;
    }

    public ParkingZoneDTO(int id, String type) {
        this.parkingZoneId = id;
        this.type = type;
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

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
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
