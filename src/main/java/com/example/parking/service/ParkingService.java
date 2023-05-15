package com.example.parking.service;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.exception.ParkingException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ParkingService {
    List<ParkingDTO> findAllParkings();
    ParkingDTO findParkingById(int id);
    Parking saveParking(ParkingDTO parkingDTO);
    public Boolean findIfParkingExistById(int parkingId);
}
