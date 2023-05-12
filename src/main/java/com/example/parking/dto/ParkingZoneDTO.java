package com.example.parking.dto;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneDTO {
    private final List<ParkingSpotDTO> parkingSpotDTOList;
    private int zoneIndex;
    private String type;

    public ParkingZoneDTO() {
        this.parkingSpotDTOList = new ArrayList<>();
    }

    public ParkingZoneDTO(List<ParkingSpotDTO> parkingSpotDTOList, int zoneIndex) {
        this.parkingSpotDTOList = parkingSpotDTOList;
        this.zoneIndex = zoneIndex;
    }

    public List<ParkingSpotDTO> getParkingSpotDTOList() {
        return parkingSpotDTOList;
    }

    public int getZoneIndex() {
        return zoneIndex;
    }

    public void setZoneIndex(int zoneIndex) {
        this.zoneIndex = zoneIndex;
    }
}
