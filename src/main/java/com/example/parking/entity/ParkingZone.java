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


    @Column(name = "type")
    private String type;

    @Column(name ="name")
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "park_id",referencedColumnName = "id")
//    private Parking parking;

    @OneToMany(targetEntity = ParkingSpot.class,mappedBy = "id",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ParkingSpot> parkingSpots;

    public ParkingZone() {
    }

    public ParkingZone(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public ParkingZone(int id, String type, String name, List<ParkingSpot> parkingSpots) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.parkingSpots = parkingSpots;
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
