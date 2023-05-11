package com.example.parking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_zone")
public class ParkingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "park_id")
    private Parking parking;

    @Column(name = "type")
    private String type;

    public ParkingZone() {
    }

    public ParkingZone(String type) {
        this.type = type;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
