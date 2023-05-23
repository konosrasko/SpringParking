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

    @Override
    public String toString() {
        return "ParkingDTO{" +
                "parkingId=" + parkingId +
                ", name='" + name + '\'' +
                ", parkingZoneDTOList=" + parkingZoneDTOList +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingDTO that = (ParkingDTO) o;
        return parkingId == that.parkingId && Objects.equals(name, that.name) && Objects.equals(parkingZoneDTOList, that.parkingZoneDTOList);
    }
}
