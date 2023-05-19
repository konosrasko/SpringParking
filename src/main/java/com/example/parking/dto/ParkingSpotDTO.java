package com.example.parking.dto;

import com.example.parking.entity.ParkingSpot;

public class ParkingSpotDTO {
    private int id;
    private String name;
    private String type;
    private boolean occupied;

    public ParkingSpotDTO(){

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
}
