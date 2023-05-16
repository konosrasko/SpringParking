package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.dto.ParkingZoneDTO;
import com.example.parking.entity.Parking;
import com.example.parking.entity.ParkingZone;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepo parkingRepo;

    private Boolean exist;





    @Autowired
    public ParkingServiceImpl(ParkingRepo parkingRepo) {
        this.parkingRepo = parkingRepo;
    }

    @Override
    public List<ParkingDTO> findAllParkings() {
        //when(this.parkingRepo.findAll()).thenReturn(new ArrayList<>());
       // when(this.parkingRepo.findAll()).thenReturn(Arrays.asList(new Parking(id,name)));
        return parkingRepo.findAll()
                .stream()
                .map(parking -> new ParkingDTO(
                        parking.getId(),
                        parking.getName()
                )).collect(Collectors.toList());
}
    @Override
    public ParkingDTO findParkingById(int id){

        Optional<Parking> result = parkingRepo.findById(id);
        Parking parking;
        ParkingDTO parkingDTO = new ParkingDTO();
        if(result.isPresent()){
            parking = result.get();
            List<ParkingZone> listOfParkingZones = parking.getParkingZones();
            List<ParkingZoneDTO> parkingZoneDTOList =listOfParkingZones.stream().map(parkingZone -> new ParkingZoneDTO(
                    parkingZone.getId(),
                    parkingZone.getType()
            )).collect(Collectors.toList());
            parkingDTO.setName(parking.getName());
            parkingDTO.setParkingId(parking.getId());
            parkingDTO.setParkingZoneDTOList(parkingZoneDTOList);
        }else{
            throw new ParkingException("There is no parking with id: " + id);
        }


        return parkingDTO;
    }

    public Boolean findIfParkingExistById(int parkingId){

        Optional<Parking> result = parkingRepo.findById(parkingId);

        exist = result.isPresent();

        return exist;
    }


    @Override
    public Parking saveParking(ParkingDTO parkingDTO){

        Optional<Parking> parkingByName = parkingRepo.findParkingByName(parkingDTO.getName());

        if(parkingByName.isPresent()){
            throw new ParkingException("Parking with name " + parkingDTO.getName() + " already exists!");
        }
        Parking parking = new Parking();
        parking.setName(parkingDTO.getName());
        return parkingRepo.save(parking);
    }
}
