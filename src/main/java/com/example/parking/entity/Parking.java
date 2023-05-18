package com.example.parking.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(targetEntity = ParkingZone.class, mappedBy = "id",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ParkingZone> parkingZones;

    public Parking() {
    }

    public Parking(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Parking(int id, String name, List<ParkingZone> parkingZones) {
        this.id = id;
        this.name = name;
        this.parkingZones = parkingZones;
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

    public List<ParkingZone> getParkingZones() {
        return parkingZones;
    }

    public void setParkingZones(List<ParkingZone> parkingZones) {
        this.parkingZones = parkingZones;
    }
}
