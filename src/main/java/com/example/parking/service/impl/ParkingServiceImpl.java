package com.example.parking.service.impl;

import com.example.parking.dto.ParkingDTO;
import com.example.parking.entity.Parking;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingRepo;
import com.example.parking.service.ParkingService;
import com.example.parking.service.ParkingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private final ParkingRepo parkingRepo;
    @Autowired
    private final ParkingZoneService parkingZoneService;
    @Autowired
    public List<ParkingDTO> parkingDTOList;

    public ParkingServiceImpl(ParkingRepo parkingRepo, ParkingZoneService parkingZoneService) {
        this.parkingRepo = parkingRepo;
        this.parkingZoneService = parkingZoneService;
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
        Parking parking = null;
        ParkingDTO parkingDTO = new ParkingDTO();
        if(result.isPresent()){
            parking = result.get();
            parkingDTO.setName(parking.getName());
            parkingDTO.setParkingId(parking.getId());
            parkingDTO.setParkingZoneDTOList(this.parkingZoneService.findParkingZonesByParkingId(parking.getId()));
        }else{
            throw new ParkingException("There is no parking with id: " + id);
        }


        return parkingDTO;
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
