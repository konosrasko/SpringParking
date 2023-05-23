package com.example.parking.dto;

import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingZoneDTO {
    private int parkingZoneId;
    private String type;
    private String name;
    private List<ParkingSpotDTO> parkingSpotDTOList;

    public ParkingZoneDTO() {
    }

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

    public void setParkingSpotDTOList(List<ParkingSpotDTO> parkingSpotDTOList) {
        this.parkingSpotDTOList = parkingSpotDTOList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingZoneDTO that)) return false;
        return getParkingZoneId() == that.getParkingZoneId() && Objects.equals(getType(), that.getType()) && Objects.equals(getName(), that.getName()) && Objects.equals(getParkingSpotDTOList(), that.getParkingSpotDTOList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingZoneId(), getType(), getName(), getParkingSpotDTOList());
    }

    @Override
    public String toString() {
        return "ParkingZoneDTO{" +
                "parkingZoneId=" + parkingZoneId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", parkingSpotDTOList=" + parkingSpotDTOList +
                '}';
    }
}
