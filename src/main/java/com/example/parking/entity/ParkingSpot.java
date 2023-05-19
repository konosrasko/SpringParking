package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {

    @Column(name = "park_id")
    private int parkingId;

    @Column(name = "zone_id")
    private int zoneId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public ParkingSpot(int id, String name, String type, boolean occupied) {
        this.id = id;
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
