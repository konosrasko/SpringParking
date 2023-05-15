package com.example.parking.dto;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDTO {
    private List<ParkingSpotDTO> parkingSpotDTOList;
    private int parkingZoneId;
    private String type;
    private int parkingId;

    public ParkingZoneDTO(int id, String type) {
        this.parkingZoneId = id;
        this.type = type;
    }

    public ParkingZoneDTO(List<ParkingSpotDTO> parkingSpotDTOList) {
       super();
       this.parkingSpotDTOList = parkingSpotDTOList;
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
}
