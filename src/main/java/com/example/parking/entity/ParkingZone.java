package com.example.parking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parking_zone")
public class ParkingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "park_id")
    private int parkingId;

    @Column(name = "type")
    private String type;

    public ParkingZone() {
    }

    public ParkingZone(String type) {
        this.type = type;
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
