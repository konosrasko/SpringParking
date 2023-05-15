package com.example.parking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Parking")
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "parking", fetch = FetchType.LAZY)
    private List<ParkingZone> parkingZones;

    public Parking() {
    }

    public Parking(int id, String name) {
        this.id = id;
        this.name = name;
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
}
