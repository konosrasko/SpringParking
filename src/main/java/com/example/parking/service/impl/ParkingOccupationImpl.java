package com.example.parking.service.impl;

import com.example.parking.dto.ParkingOccupationDTO;
import com.example.parking.entity.*;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.*;
import com.example.parking.service.ParkingOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingOccupationImpl implements ParkingOccupationService {
    @Autowired
    private ParkingRepo parkingRepo;
    @Autowired
    private ParkingZoneRepo parkingZoneRepo;
    @Autowired
    private ParkingSpotRepo parkingSpotRepo;
    @Autowired
    private ParkingOccupationRepo parkingOccupationRepo;
    @Autowired
    private PriceListRepo priceListRepo;
    @Autowired
    private PriceScaleRepo priceScaleRepo;
    @Override
    public List<ParkingOccupationDTO> getParkingHistoryByParkingId(int parkingId) {
        Optional<Parking> parking = parkingRepo.findById(parkingId);
        if(parking.isEmpty()) {
            throw new ParkingException("Parking does not exist");
        } else {
            return parking.get().getParkingZones()
                    .stream()
                    .flatMap(parkingZone ->  parkingZone.getParkingSpots()
                            .stream()
                            .flatMap(parkingSpot -> parkingOccupationRepo.findAll()
                                    .stream()
                                    .filter(parkingOccupation -> parkingOccupation.getParkingSpot().getId() == parkingSpot.getId())
                                    .map(ParkingOccupationDTO::new)
                            )
                    ).toList();
        }
    }

    @Override
    public ParkingOccupationDTO saveParkingOccupation(int spotId, ParkingOccupationDTO parkingOccupationDTO) {
        ParkingOccupation savedOccupation = new ParkingOccupation();
        Optional<ParkingSpot> parkingSpot = parkingSpotRepo.findById(spotId);
        if(parkingSpot.isEmpty()){
            throw new ParkingException("Parking spot does not exist"); //response error 404
        } else {
            if(parkingSpot.get().isOccupied()){
                throw new RuntimeException("Parking spot is occupied"); //response error 400
            } else {
                ParkingOccupation parkingOccupation = new ParkingOccupation(parkingOccupationDTO);
                parkingSpot.get().setOccupied(true);
                parkingOccupation.setParkingSpot(parkingSpotRepo.save(parkingSpot.get()));
                savedOccupation= parkingOccupationRepo.save(parkingOccupation);
                parkingOccupationDTO.setSpotId(spotId);
            }
        }
        return new ParkingOccupationDTO(savedOccupation);
    }

    @Override
    public ParkingOccupationDTO updateParkingHistoryOccupation(int spotId) {
        Optional<ParkingSpot> parkingSpot = parkingSpotRepo.findById(spotId);
        if(parkingSpot.isEmpty()){
            throw new ParkingException("Parking spot does not exist"); //response error 404
        } else {
            if (!parkingSpot.get().isOccupied()) {
                throw new RuntimeException("Parking spot is empty"); //response error 400
            } else {
                Optional<ParkingOccupation> optionalParkingOccupation = parkingOccupationRepo.findAll()
                        .stream()
                        .filter(parkingOccupation -> parkingOccupation.getParkingSpot().getId() == parkingSpot.get().getId())
                        .max(Comparator.comparing(ParkingOccupation::getOccupationDate));
                if(optionalParkingOccupation.isEmpty()){
                    throw new RuntimeException("No occupation for spot with id : "+spotId);
                } else {
                    parkingSpot.get().setOccupied(false);
                    parkingSpotRepo.save(parkingSpot.get());
                    optionalParkingOccupation.get().setVacancyDate(OffsetDateTime.now());
                    long totalDur= optionalParkingOccupation.get().totalDur();
                    ParkingSpot spot = parkingSpot.get();
                    int id  = spot.getZone().getId();
                    Optional<ParkingZone> zone = parkingZoneRepo.findById(id);
                    List<PriceList> priceLists = zone.get().getPriceLists();
                    PriceList firstPriceList = priceLists.get(0);
                    float cost = (float) firstPriceList.totalCost(totalDur);
                    ParkingOccupation updateOcc = optionalParkingOccupation.get();
                    updateOcc.setCost(cost);
                    //optionalParkingOccupation.get().setCost((float) parkingSpot.get().getZone().getPriceLists().get(0).totalCost(totalDur)); // cost implementation method
                    return new ParkingOccupationDTO(parkingOccupationRepo.save(updateOcc));
                }
            }
        }
    }



}
