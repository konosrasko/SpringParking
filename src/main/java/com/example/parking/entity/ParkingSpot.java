package com.example.parking.entity;

import jakarta.persistence.*;

@Entity(name = "ParkingSpot")
@Table(name = "parking_spot")
public class ParkingSpot {

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

    @Column(name ="zone_id")
    private int zone_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id",referencedColumnName = "id",updatable = false,insertable = false)
    private ParkingSpot spot;

    public ParkingSpot() {
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
