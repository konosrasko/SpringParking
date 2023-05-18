package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
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

    @Override
    public List<ParkingDTO> findAllParkings() {
        return parkingRepo.findAll()
                .stream()
                .map(ParkingDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public ParkingDTO findParkingById(int id){
        Optional<Parking> result = parkingRepo.findById(id);
        if(result.isPresent()){
            return new ParkingDTO(result.get());
        }else{
            throw new ParkingException("There is no parking with id: " + id);
        }
    }

    public Boolean findIfParkingExistById(int parkingId){
        return parkingRepo.existsById(parkingId);
    }

    @Override
    public Parking saveParking(ParkingDTO parkingDTO){

        Optional<Parking> parkingByName = parkingRepo.findParkingByName(parkingDTO.getName());

        if(parkingByName.isPresent()){
            throw new ParkingException("Parking with name " + parkingDTO.getName() + " already exists!");
        } else {
            Parking parking = new Parking();
            parking.setName(parkingDTO.getName());
            parking.setId(parkingDTO.getParkingId());
            parking.setParkingZones(parking.getParkingZones());
            return parkingRepo.save(parking);
        }
    }

    @Override
    public ParkingZone addZone(int parkingId, ParkingZoneDTO parkingZoneDTO){
        if(parkingRepo.existsById(parkingId)){
            ParkingZone parkingZone = new ParkingZone(
                    parkingZoneDTO.getParkingZoneId(),
                    parkingZoneDTO.getName(),
                    parkingZoneDTO.getType()
            );
            return parkingZoneRepo.save(parkingZone);
        } else {
            throw new ParkingException("Parking with id : "+ parkingId +" does not exist");
        }
    }
    @Override
    public  List<ParkingZoneDTO> getParkingZones(int parkingId){
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if (parking.isPresent()) {
            return parking.get().getParkingZones().stream().map(ParkingZoneDTO::new).toList();
        } else {
            throw new ParkingException("Parking with id : "+ parkingId +" does not exist");
        }
    }
    @Override
    public ParkingZoneDTO getParkingZoneById(int parkingId, int zoneId){
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if (parking.isPresent()) {
            return parking.get().getParkingZones().stream()
                    .filter(parkingZone -> parkingZone.getId() == zoneId)
                    .findFirst()
                    .map(ParkingZoneDTO::new)
                    .orElseThrow(()->new ParkingException("Zone with id : "+ zoneId +" does not exist"));
        } else {
            throw new ParkingException("Parking with id : "+ parkingId +" does not exist");
        }
    }
}
