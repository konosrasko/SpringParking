package com.example.parking.dto;

import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingDTO {
    private int parkingId;
    private String name;
    private List<ParkingZoneDTO> parkingZoneDTOList;

    public ParkingDTO() {
    }

    public ParkingDTO(int parkingId, String name) {
        this.parkingId = parkingId;
        this.name = name;
        this.parkingZoneDTOList = new ArrayList<>();
    }

    public ParkingDTO(String name){
        this.name = name;
        this.parkingZoneDTOList = new ArrayList<>();
    }

    public ParkingDTO(Parking parking) {
        this.parkingId = parking.getId();
        this.name = parking.getName();
        this.parkingZoneDTOList = parking.getParkingZones()
                .stream()
                .map(ParkingZoneDTO::new)
                .toList();
    }

    public void setParkingZoneDTOList(List<ParkingZoneDTO> parkingZoneDTOList) {
        this.parkingZoneDTOList = parkingZoneDTOList;
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
        if(parkingZoneDTOList == null){
            return new ArrayList<>();
        }
        return parkingZoneDTOList;
    }


    public ParkingDTO(String name, List<ParkingZoneDTO> parkingZoneDTOList) {
        this.name = name;
        this.parkingZoneDTOList = parkingZoneDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingDTO that)) return false;
        return getParkingId() == that.getParkingId() && Objects.equals(getName(), that.getName()) && Objects.equals(getParkingZoneDTOList(), that.getParkingZoneDTOList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingId(), getName(), getParkingZoneDTOList());
    }

    @Override
    public String toString() {
        return "ParkingDTO{" +
                "parkingId=" + parkingId +
                ", name='" + name + '\'' +
                ", parkingZoneDTOList=" + parkingZoneDTOList +
                '}';
    }
}
