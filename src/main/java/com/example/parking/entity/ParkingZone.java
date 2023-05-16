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

    @Column(name ="name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "park_id",referencedColumnName = "id",updatable = false,insertable = false)
    private Parking parking;




    @OneToMany(targetEntity = ParkingSpot.class,cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots;

    public ParkingZone() {
    }

    public ParkingZone(int id, int parkingId, String type, String name) {
        this.id = id;
        this.parkingId = parkingId;
        this.type = type;
        this.name = name;
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

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public List<ParkingSpot> getParkingSpots(){
        return parkingSpots;
    }
    public void setParkingZoneSpots(List<ParkingSpot> parkingSpots){
        this.parkingSpots = parkingSpots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
