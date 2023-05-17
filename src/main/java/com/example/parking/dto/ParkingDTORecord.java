package com.example.parking.dto;

import com.example.parking.entity.Parking;

import java.util.List;

public record ParkingDTORecord(int parkingId, String name, List<ParkingZoneDTO> parkingZoneDTOList) {
    public ParkingDTORecord(Parking parking) {
        this(
                parking.getId(),
                parking.getName(),
                parking.getParkingZones()
                        .stream()
                        .map(ParkingZoneDTO::new)
                        .toList()
        );
    }
}
