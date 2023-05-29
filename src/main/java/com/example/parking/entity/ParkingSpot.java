package com.example.parking.entity;

import com.example.parking.dto.ParkingSpotDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "occupied")
    private boolean occupied;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    private ParkingZone zone;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "parkingSpot")
    private ParkingOccupation parkingOccupation;

    public ParkingSpot(ParkingZone zone, ParkingSpotDTO parkingSpotDTO) {
        this.id = parkingSpotDTO.getId();
        this.name = parkingSpotDTO.getName();
        this.type = parkingSpotDTO.getType();
        this.occupied = parkingSpotDTO.isOccupied();
        this.zone = zone;
    }
}
