package com.example.parking.dto;

import com.example.parking.entity.ParkingZone;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingZoneDTO {
    private int parkingZoneId;
    private String type;
    private String name;
    private List<ParkingSpotDTO> parkingSpotDTOList;
    private float capacityPercentage;

    public ParkingZoneDTO(ParkingZone parkingZone){
        this.parkingZoneId = parkingZone.getId();
        this.type = parkingZone.getType();
        this.name = parkingZone.getName();
        this.parkingSpotDTOList = parkingZone.getParkingSpots()
                .stream()
                .map(ParkingSpotDTO::new)
                .toList();
        this.capacityPercentage = parkingZone.zoneCapacityPercentage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingZoneDTO that)) return false;
        return getParkingZoneId() == that.getParkingZoneId() && Objects.equals(getType(), that.getType()) && Objects.equals(getName(), that.getName()) && Objects.equals(getParkingSpotDTOList(), that.getParkingSpotDTOList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingZoneId(), getType(), getName(), getParkingSpotDTOList());
    }

    public float getCapacityPercentage() {
        return capacityPercentage;
    }

    public void setCapacityPercentage(float capacityPercentage) {
        this.capacityPercentage = capacityPercentage;
    }
}
