package com.example.parking.entity;

import com.example.parking.dto.ParkingZoneDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parking_zone")
public class ParkingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "park_id", referencedColumnName = "id")
    private Parking parking;

    public ParkingZone() {
        this.parkingSpots = new ArrayList<>();
    }

    public ParkingZone(String type, String name) {
        this.type = type;
        this.name = name;
        this.parkingSpots = new ArrayList<>();
    }

    public ParkingZone(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.parkingSpots = new ArrayList<>();
    }

    public ParkingZone(int id, String type, String name, List<ParkingSpot> parkingSpots) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.parkingSpots = parkingSpots;
    }

    public ParkingZone(ParkingZoneDTO parkingZoneDTO) {
        this.id = parkingZoneDTO.getParkingZoneId();
        this.name = parkingZoneDTO.getName();
        this.type = parkingZoneDTO.getType();
        this.parkingSpots = parkingZoneDTO.getParkingSpotDTOList()
                .stream()
                .map(ParkingSpot::new)
                .toList();
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

    public List<ParkingSpot> getParkingSpots() {

        return parkingSpots;
    }

    public void setParkingZoneSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getName(), getParkingSpots(), parking);
    }

    @Override
    public String toString() {
        return "ParkingZone{" +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", parkingSpots=" + parkingSpots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingZone that)) return false;
        return getId() == that.getId() && Objects.equals(getType(), that.getType()) && Objects.equals(getName(), that.getName()) && Objects.equals(getParkingSpots(), that.getParkingSpots()) && Objects.equals(getParking(), that.getParking());
    }

}
