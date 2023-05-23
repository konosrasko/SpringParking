package com.example.parking.dto;

import com.example.parking.entity.ParkingSpot;

import java.util.Objects;

public class ParkingSpotDTO {
    private int id;
    private String name;
    private String type;
    private boolean occupied;

    public ParkingSpotDTO(){
    }

    public ParkingSpotDTO(String name, String type, boolean occupied) {
        this.name = name;
        this.type = type;
        this.occupied = occupied;
    }

    public ParkingSpotDTO(int id, String name, String type, boolean occupied) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.occupied = occupied;
    }

    public ParkingSpotDTO(ParkingSpot parkingSpot){
        this.id = parkingSpot.getId();
        this.name = parkingSpot.getName();
        this.type = parkingSpot.getType();
        this.occupied = parkingSpot.isOccupied();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSpotDTO that)) return false;
        return getId() == that.getId() && isOccupied() == that.isOccupied() && Objects.equals(getName(), that.getName()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), isOccupied());
    }

    @Override
    public String toString() {
        return "ParkingSpotDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", occupied=" + occupied +
                '}';
    }
}
