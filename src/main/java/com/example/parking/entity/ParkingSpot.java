package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "park_id")
    private int parkingId;

    @Column(name = "zone_id")
    private int zoneId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "occupied")
    private boolean occupied;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", referencedColumnName = "id", updatable = false, insertable = false)
    private ParkingZone zone;

    public ParkingSpot() {
    }

    public ParkingSpot(int parkingId, int zoneId, ParkingSpotDTO parkingSpotDTO) {
        this.parkingId = parkingId;
        this.zoneId = zoneId;
        this.id = parkingSpotDTO.getId();
        this.name = parkingSpotDTO.getName();
        this.type = parkingSpotDTO.getType();
        this.occupied = parkingSpotDTO.isOccupied();
    }

    public ParkingSpot(String name, String type, boolean occupied) {
        //this.id = id;
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

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSpot that)) return false;
        return parkingId == that.parkingId && zoneId == that.zoneId && getId() == that.getId() && isOccupied() == that.isOccupied() && Objects.equals(getName(), that.getName()) && Objects.equals(getType(), that.getType()) && Objects.equals(zone, that.zone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingId, zoneId, getId(), getName(), getType(), isOccupied(), zone);
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "parkingId=" + parkingId +
                ", zoneId=" + zoneId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", occupied=" + occupied +
                '}';
    }
}
