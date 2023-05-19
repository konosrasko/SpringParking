package com.example.parking.service.impl;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingService;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingZoneServiceImpl implements ParkingZoneService {

    @Autowired
    private final ParkingRepo parkingRepo;


    @Autowired
    private ParkingService parkingService;


    @Autowired
    public ParkingZoneServiceImpl(ParkingZoneRepo parkingZoneRepo, ParkingSpotRepo parkingSpotRepo, ParkingRepo parkingRepo) {
        this.parkingRepo = parkingRepo;
    }
}