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
/*
//    @Override
//    public List<ParkingZoneDTO> findParkingZonesByParkingId(int parkingId) {
//        return this.parkingRepo.findById(parkingId)
//                .stream()
//                .flatMap(parking -> {
//                    return parking.getParkingZones()
//                            .stream()
//                            .map(ParkingZoneDTO::new);
//                }).toList();
//    }
//
//    @Override
//    public ParkingZoneDTO findParkingZoneById(int zoneId) {
//
//        Optional<ParkingZone> result = parkingZoneRepo.findById(zoneId);
//        ParkingZone parkingZone =null;
//        ParkingZoneDTO parkingZoneDTO = new ParkingZoneDTO();
//        if(result.isPresent()){
//            parkingZone =result.get();
//            List<ParkingSpot> spotList = parkingZone.getParkingSpots();
//            List<ParkingSpotDTO> parkingSpotDTOList = spotList.stream().map(parkingSpot -> new ParkingSpotDTO(
//                    parkingSpot.getId(),
//                    parkingSpot.getZoneId(),
//                    parkingSpot.getName(),
//                    parkingSpot.getType(),
//                    parkingSpot.isOccupied()
//            )).collect(Collectors.toList());
//            parkingZoneDTO.setParkingZoneId(parkingZone.getId());
//            parkingZoneDTO.setType(parkingZone.getType());
//            parkingZoneDTO.setName(parkingZone.getName());
//            parkingZoneDTO.setParkingSpotDTOList(parkingSpotDTOList);
//        }else{
//            throw new ParkingException("There is no zone with id:"+ zoneId);
//        }
//        return parkingZoneDTO;
//    }
    @Override
    public ParkingZone saveParkingZone(ParkingZoneDTO parkingZoneDTO) {

        Optional<ParkingZone> parkingZoneType = parkingZoneRepo.findById(parkingZoneDTO.getParkingZoneId());


        if(parkingZoneType.isPresent()){
            throw new ParkingException("ParkingZone not valid");
        }
        ParkingZone parkingZone = new ParkingZone();
        parkingZone.setType(parkingZoneDTO.getType());
        //parkingZone.setParkingId(parkingZoneDTO.getParkingId());
        return parkingZoneRepo.save(parkingZone);

    }

    @Override
    public void deleteZone(ParkingZone parkingZone) {

    }*/
}
