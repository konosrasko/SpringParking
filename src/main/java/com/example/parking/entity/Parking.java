package com.example.parking.entity;

import com.example.parking.dto.ParkingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.*;

@Builder
@Entity
@Table(name = "parking")
@AllArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parking", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingZone> parkingZones;

    public Parking() {
        this.parkingZones = new ArrayList<>();
    }



    public Parking(String name) {
        this.name = name;
        this.parkingZones = new ArrayList<>();
    }

    public Parking(ParkingDTO parkingDTO) {
        this.id = parkingDTO.getParkingId();
        this.name = parkingDTO.getName();
        this.parkingZones = parkingDTO.getParkingZoneDTOList()
                .stream()
                .map(parkingZoneDTO -> new ParkingZone(this,parkingZoneDTO))
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

    public List<ParkingZone> getParkingZones() {
        return parkingZones;
    }

    public void setParkingZones(List<ParkingZone> parkingZones) {
        this.parkingZones = parkingZones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parking parking)) return false;
        return getId() == parking.getId() && Objects.equals(getName(), parking.getName()) && Objects.equals(getParkingZones(), parking.getParkingZones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getParkingZones());
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parkingZones=" + parkingZones +
                '}';
    }
}
