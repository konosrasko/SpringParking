package com.example.parking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "zone_id")
    private int zoneId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "occupied")
    private boolean occupied;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id",referencedColumnName = "id",updatable = false,insertable = false)
    private ParkingSpot spot;

    public ParkingSpot() {
    }

    public ParkingSpot(int id, int zoneId, String name, String type, boolean occupied, ParkingSpot spot) {
        this.id = id;
        this.zoneId = zoneId;
        this.name = name;
        this.type = type;
        this.occupied = occupied;
        this.spot = spot;
    }

    public int getId() {
        return id;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
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
