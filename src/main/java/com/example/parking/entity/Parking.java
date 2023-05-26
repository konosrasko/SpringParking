package com.example.parking.entity;

import com.example.parking.dto.ParkingDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parking", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingZone> parkingZones;

    public Parking(ParkingDTO parkingDTO) {
        this.id = parkingDTO.getParkingId();
        this.name = parkingDTO.getName();
        if(parkingDTO.getParkingZoneDTOList()==null){
            this.parkingZones = new ArrayList<>();
        }else{
            this.parkingZones = parkingDTO.getParkingZoneDTOList()
                .stream()
                .map(parkingZoneDTO -> new ParkingZone(this,parkingZoneDTO))
                .toList();
        }
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
}
