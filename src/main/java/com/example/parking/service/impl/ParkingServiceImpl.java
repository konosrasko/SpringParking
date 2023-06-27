package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingSpotDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingSpot;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.repository.ParkingSpotRepo;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepo parkingRepo;
    @Autowired
    private ParkingZoneRepo parkingZoneRepo;
    @Autowired
    private ParkingSpotRepo parkingSpotRepo;

    @Override
    public List<ParkingDTO> findAllParkings() {
        return parkingRepo.findAll().stream().map(ParkingDTO::new).collect(Collectors.toList());
    }

    @Override
    public ParkingDTO findParkingById(int id) {
        Optional<Parking> result = parkingRepo.findById(id);
        if (result.isPresent()) {
            return new ParkingDTO(result.get());
        } else {
            throw new ParkingException("There is no parking with id: " + id);
        }
    }

    @Override
    public ParkingDTO addParking(ParkingDTO parkingDTO) {
        Parking parking = new Parking(parkingDTO);
        return new ParkingDTO(parkingRepo.save(parking));
    }

    @Override
    public ParkingZoneDTO addZone(int parkingId, ParkingZoneDTO parkingZoneDTO) {
        Optional<Parking> foundParking = parkingRepo.findById(parkingId);
        if (foundParking.isPresent()) {
            ParkingZone zone = new ParkingZone(foundParking.get(), parkingZoneDTO);
            return new ParkingZoneDTO(parkingZoneRepo.save(zone));
        } else {
            throw new ParkingException("Parking with id : " + parkingId + " does not exist");
        }
    }

    @Override
    public ParkingSpotDTO addSpot(ParkingSpotDTO parkingSpotDTO, int zoneId) {
        Optional<ParkingZone> foundZone = parkingZoneRepo.findById(zoneId);
        if (foundZone.isPresent()) {
            ParkingSpot spot = new ParkingSpot(foundZone.get(), parkingSpotDTO);
            return new ParkingSpotDTO(parkingSpotRepo.save(spot));
        } else {
            throw new ParkingException("The zone with id: " + zoneId + " doesn't exists! ");
        }
    }

    @Override
    public List<ParkingZoneDTO> getParkingZones(int parkingId) {
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if (parking.isPresent()) {
            return parking.get().getParkingZones().stream().map(ParkingZoneDTO::new).toList();
        } else {
            throw new ParkingException("Parking with id : " + parkingId + " does not exist");
        }
    }

    @Override
    public ParkingZoneDTO getParkingZoneById(int parkingId, int zoneId) {
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if (parking.isPresent()) {
            return parking.get().getParkingZones()
                    .stream()
                    .filter(parkingZone -> parkingZone.getId() == zoneId)
                    .findFirst()
                    .map(ParkingZoneDTO::new)
                    .orElseThrow(() -> new ParkingException("Zone with id : " + zoneId + " does not exist"));
        } else {
            throw new ParkingException("Parking with id : " + parkingId + " does not exist");
        }
    }

    @Override
    public ParkingSpotDTO findParkingSpotById(int id) {
        Optional<ParkingSpot> results = parkingSpotRepo.findById(id);
        if (results.isPresent()) {
            return new ParkingSpotDTO(results.get());
        } else {
            throw new ParkingException("There are no spots with the id: " + id);
        }
    }

    @Override
    public List<ParkingSpotDTO> findSpotsByZoneId(int zoneId) {
        Optional<ParkingZone> parkingZone = parkingZoneRepo.findById(zoneId);
        if (parkingZone.isPresent()) {
            return parkingZone.get().getParkingSpots().stream().map(ParkingSpotDTO::new).toList();
        } else {
            throw new ParkingException("There are no spots in zone with id " + zoneId);
        }
    }

    @Override
    public void deleteSpot(int zoneId, int spotId) {
        if (parkingZoneRepo.existsById(zoneId)) {
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

    public Boolean findIfParkingExistById(int parkingId) {
        return parkingRepo.existsById(parkingId);
    }
    @Override
    public List<ParkingSpotDTO> findEmptySpots(int parkingId){
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if(parking.isPresent()){
            return parking.get().findEmptySpots().stream()
                    .map(ParkingSpotDTO::new)
                    .toList();
        } else {
            throw new ParkingException("Parking does not exist");
        }
    }
    @Override
    public void deleteParking(int parkingId){
        if (parkingRepo.existsById(parkingId)){
            parkingRepo.deleteById(parkingId);
        }else
            throw new ParkingException("there is no Parking with ID :"+parkingId);
    }
}
