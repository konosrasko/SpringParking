package com.example.parking.service;

import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.entity.ParkingOccupation;

import java.util.List;

public interface ParkingOccupationService {
    List<ParkingOccupationDTO> getParkingHistoryByParkingId(int parkingId);
    void saveParkingOccupation(int spotId, ParkingOccupationDTO parkingOccupationDTO);
    ParkingOccupationDTO updateParkingOccupation(ParkingOccupation parkingOccupation);
}
