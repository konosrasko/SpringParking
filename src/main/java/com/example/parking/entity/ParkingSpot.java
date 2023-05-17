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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "zone_id", referencedColumnName = "id", updatable = false, insertable = false)
    private ParkingZone zone;

    public ParkingSpot() {
    }

    public ParkingSpot(int id, int zoneId, String name, String type, boolean occupied) {
        this.id = id;
        this.zoneId = zoneId;
        this.name = name;
        this.type = type;
        this.occupied = occupied;
    }

    public int getId() {
        return id;
    }

    public int getZoneId() {
        return zoneId;
    }

    public ParkingZone getZone() {
        return zone;
    }

    public void setZone(ParkingZone zone) {
        this.zone = zone;
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
