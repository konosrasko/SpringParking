package com.example.parking.service;

import com.example.parking.dto.ParkingOccupationDTO;

import java.util.List;

public interface ParkingOccupationService {
    List<ParkingOccupationDTO> getParkingHistoryByParkingId(int parkingId);
    ParkingOccupationDTO saveParkingOccupation(int spotId, ParkingOccupationDTO parkingOccupationDTO);
    ParkingOccupationDTO updateParkingHistoryOccupation(int spotId);
}
