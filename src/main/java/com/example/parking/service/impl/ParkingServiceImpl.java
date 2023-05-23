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
import org.springframework.beans.BeanUtils;
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
        return parkingRepo.findAll()
                .stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
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

    public Boolean findIfParkingExistById(int parkingId) {
        return parkingRepo.existsById(parkingId);
    }

    @Override
    public Parking saveParking(ParkingDTO parkingDTO) {
        Parking parking = new Parking();
        parking.setName(parkingDTO.getName());

        Parking savedParking = parkingRepo.save(parking);

        List<ParkingZoneDTO> foundZones = parkingDTO.getParkingZoneDTOList();


        for(ParkingZoneDTO zoneDTO : foundZones){
            ParkingZone zone = new ParkingZone();
            zone.setName(zoneDTO.getName());
            zone.setType(zoneDTO.getType());
            zone.setParking(savedParking);

            ParkingZone savedZone = parkingZoneRepo.save(zone);

            List<ParkingSpotDTO> foundSpots = zoneDTO.getParkingSpotDTOList();


            for(ParkingSpotDTO spotDTO : foundSpots){
                ParkingSpot spot = new ParkingSpot();
                spot.setName(spotDTO.getName());
                spot.setType(spotDTO.getType());
                spot.setOccupied(spot.isOccupied());
                spot.setZone(savedZone);

                parkingSpotRepo.save(spot);

            }

        }
        return new Parking(parkingDTO);
    }

    @Override
    public ParkingZone addZone(int parkingId, ParkingZoneDTO parkingZoneDTO) {
        if (parkingRepo.existsById(parkingId)) {
            ParkingZone parkingZone = new ParkingZone(
                    parkingZoneDTO.getParkingZoneId(),
                    parkingZoneDTO.getName(),
                    parkingZoneDTO.getType());
            return parkingZoneRepo.save(parkingZone);
        } else {
            throw new ParkingException("Parking with id : " + parkingId + " does not exist");
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
            return parking.get().getParkingZones().stream()
                    .filter(parkingZone -> parkingZone.getId() == zoneId)
                    .findFirst()
                    .map(ParkingZoneDTO::new)
                    .orElseThrow(() -> new ParkingException("Zone with id : " + zoneId + " does not exist"));
        } else {
            throw new ParkingException("Parking with id : " + parkingId + " does not exist");
        }
    }

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
    public void createNewSpot(ParkingSpotDTO parkingSpotDTO, int zoneId, int parkingId) {
        if (parkingZoneRepo.existsById(zoneId)) {
            if (parkingSpotRepo.existsById(parkingSpotDTO.getId())) {
                throw new ParkingException("The zone with id: " + zoneId + " doesn't exists!");
            } else {
                parkingSpotRepo.save(new ParkingSpot(parkingSpotDTO));
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

}
