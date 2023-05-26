package com.example.parking.dto;

import com.example.parking.entity.ParkingSpot;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpotDTO {
    private int id;
    private String name;
    private String type;
    private boolean occupied;

    public ParkingSpotDTO(ParkingSpot parkingSpot) {
        this.id = parkingSpot.getId();
        this.name = parkingSpot.getName();
        this.type = parkingSpot.getType();
        this.occupied = parkingSpot.isOccupied();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSpotDTO that)) return false;
        return getId() == that.getId() && isOccupied() == that.isOccupied() && Objects.equals(getName(), that.getName()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), isOccupied());
    }

}
