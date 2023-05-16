package com.example.parking.dto;

public class ParkingSpotDTO {
    private int id;
    private int zoneId;
    private String name;
    private String type;
    private boolean occupied;

    public ParkingSpotDTO() {
    }

    public ParkingSpotDTO(int id, int zoneId, String name, String type, boolean occupied) {
        this.id = id;
        this.zoneId = zoneId;
        this.name = name;
        this.type = type;
        this.occupied = occupied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
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
