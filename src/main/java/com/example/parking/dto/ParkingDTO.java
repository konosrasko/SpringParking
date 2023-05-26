package com.example.parking.dto;

import com.example.parking.entity.Parking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingDTO {
    private int parkingId;
    private String name;
    private List<ParkingZoneDTO> parkingZoneDTOList;

    public ParkingDTO(Parking parking) {
        this.parkingId = parking.getId();
        this.name = parking.getName();
        this.parkingZoneDTOList = parking.getParkingZones()
                .stream()
                .map(ParkingZoneDTO::new)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingDTO that)) return false;
        return getParkingId() == that.getParkingId() && Objects.equals(getName(), that.getName()) && Objects.equals(getParkingZoneDTOList(), that.getParkingZoneDTOList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingId(), getName(), getParkingZoneDTOList());
    }
}
