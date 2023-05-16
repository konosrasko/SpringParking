package com.example.parking.service.impl;

import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.service.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepo parkingSpotRepo;

    @Autowired
    public ParkingSpotServiceImpl(ParkingSpotRepo parkingSpotRepo) {
        this.parkingSpotRepo = parkingSpotRepo;
    }

    public ParkingSpot dtoToEntity(ParkingSpotDTO parkingSpotDTO, ParkingSpot parkingSpot){

        if(parkingSpotDTO == null){
            return null;
        }
        if(parkingSpot == null){
            parkingSpot = new ParkingSpot();
        }

        BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);
        return parkingSpot;
    }

    public ParkingSpotDTO entityToDTO(ParkingSpot parkingSpot){
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO();
        BeanUtils.copyProperties(parkingSpot, parkingSpotDTO);
        return parkingSpotDTO;
    }

    public List<ParkingSpotDTO> findAllParkingSpots(){
        List<ParkingSpot> parkingSpots = parkingSpotRepo.findAll();
        List<ParkingSpotDTO> parkingSpotDTOS = new ArrayList<>();

        for(ParkingSpot parkingSpot : parkingSpots){
            parkingSpotDTOS.add(entityToDTO(parkingSpot));
        }

        return parkingSpotDTOS;
    }

    public ParkingSpotDTO findParkingSpotById(int id){
        Optional<ParkingSpot> results = parkingSpotRepo.findById(id);

        ParkingSpotDTO parkingSpotDTO = null;

        if(results.isPresent()){
            parkingSpotDTO = entityToDTO(parkingSpotRepo.findById(id).get());
        }else{
            throw new ParkingException("There are no spots with the id: " + id);
        }

        return parkingSpotDTO;
    }

    public List<ParkingSpotDTO> findSpotsByZoneId(int zoneId){
        List<ParkingSpot> results = parkingSpotRepo.findSpotsByZoneId(zoneId);
        List<ParkingSpotDTO> parkingSpotDTOS = new ArrayList<>();

        if(results.isEmpty()){
            throw new ParkingException("There are no spots in zone with id " + zoneId);
        }

        for(ParkingSpot parkingSpot : results){
            parkingSpotDTOS.add(entityToDTO(parkingSpot));
        }

        return parkingSpotDTOS;
    }


}
