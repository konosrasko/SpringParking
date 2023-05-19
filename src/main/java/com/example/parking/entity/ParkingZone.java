package com.example.parking.entity;

import com.example.parking.dto.ParkingZoneDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_zone")
public class ParkingZone {
    @Column(name = "park_id")
    private int parkingId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name ="name")
    private String name;



    @OneToMany(mappedBy = "zone",cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "park_id",referencedColumnName = "id",updatable = false,insertable = false)
    private Parking parking;

    public ParkingZone() {
    }

    public ParkingZone(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public ParkingZone(int id, int park_id, String type, String name) {
        this.id = id;
        this.parkingId = park_id;
        this.type = type;
        this.name = name;
    }

    public ParkingZone(int id, int parkId, String type, String name, List<ParkingSpot> parkingSpots) {
        this.id = id;
        parkingId = parkId;
        this.type = type;
        this.name = name;
        this.parkingSpots = parkingSpots;
    }

    public ParkingZone(int parkingId, ParkingZoneDTO parkingZoneDTO) {
        //this.parkingId = parkingId;
        this.id = parkingZoneDTO.getParkingZoneId();
        this.name = parkingZoneDTO.getName();
        this.type = parkingZoneDTO.getType();
        this.parkingSpots = parkingZoneDTO.getParkingSpotDTOList()
                .stream()
                .map(parkingSpotDTO -> new ParkingSpot(parkingId,id,parkingSpotDTO))
                .toList();
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
        if(parkingSpots == null ){
            return new ArrayList<>();
        }
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

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }
}
