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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingZoneServiceImpl implements ParkingZoneService {

    @Autowired
    private ParkingZoneRepo parkingZoneRepo;

    @Autowired
    private ParkingSpotRepo parkingSpotRepo;

    public ParkingSpotDTO entityToDTO(ParkingSpot parkingSpot){
        ParkingSpotDTO parkingSpotDTO = new ParkingSpotDTO();
        BeanUtils.copyProperties(parkingSpot, parkingSpotDTO);

        return parkingSpotDTO;
    }

    @Override
    public ParkingSpotDTO findParkingSpotById(int id){
        Optional<ParkingSpot> results = parkingSpotRepo.findById(id);

        ParkingSpotDTO parkingSpotDTO;

        if(results.isPresent()){
            parkingSpotDTO = entityToDTO(parkingSpotRepo.findById(id).get());
        }else{
            throw new ParkingException("There are no spots with the id: " + id);
        }

        return parkingSpotDTO;
    }

    @Override
    public List<ParkingSpotDTO> findSpotsByZoneId(int zoneId){
        //List<ParkingSpot> results = parkingSpotRepo.findSpotsByZoneId(zoneId);
        Optional<ParkingZone> parkingZone = parkingZoneRepo.findById(zoneId);
        if(parkingZone.isPresent()){
            return parkingZone.get().getParkingSpots().stream()
                    .map(ParkingSpotDTO::new)
                    .toList();
        } else {
            throw new ParkingException("There are no spots in zone with id " + zoneId);
        }

    }

    @Override
    public ParkingSpot createNewSpot(ParkingSpotDTO parkingSpotDTO, int zoneId, int parkingId) {
        ParkingSpot parkingSpot = new ParkingSpot();
        if (parkingZoneRepo.existsById(zoneId)) {
            if (parkingSpotRepo.existsById(parkingSpotDTO.getId())) {
                throw new ParkingException("The zone with id: " + zoneId + " doesn't exists!");
            } else {
                return parkingSpotRepo.save(
                        new ParkingSpot(parkingId,zoneId,parkingSpotDTO)
                );
            }
        } else {
            throw new ParkingException("The zone with id: " + zoneId + " doesn't exists!");
        }
    }

    @Override
    public void deleteSpot(int zoneId, int spotId) {
        if(parkingZoneRepo.existsById(zoneId)) {
            Optional<ParkingSpot> detectSpot = parkingSpotRepo.findById(spotId);
            if (detectSpot.isPresent()) {
                parkingSpotRepo.deleteById(spotId);
            } else {
                throw new ParkingException("There is no spot with id " + spotId);
            }
        } else {
            throw new ParkingException("There is no zone with id " + zoneId);
        }
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
