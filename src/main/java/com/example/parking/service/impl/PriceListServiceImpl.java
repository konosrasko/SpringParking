package com.example.parking.service.impl;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.entity.ParkingZone;
import com.example.parking.entity.PriceList;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.repository.PriceListRepo;
import com.example.parking.repository.PriceScaleRepo;
import com.example.parking.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PriceListServiceImpl implements PriceListService {

    @Autowired
    ParkingZoneRepo parkingZoneRepo;

    @Autowired
    PriceListRepo priceListRepo;

    @Autowired
    PriceScaleRepo priceScaleRepo;

    @Override
    public List<PriceListDTO> getPriceList(int zoneId) {
        Optional<ParkingZone> foundZone = parkingZoneRepo.findById(zoneId);
        if(foundZone.isPresent()){
            return foundZone.get().getPriceLists().stream()
                    .map(PriceListDTO::new)
                    .toList();
        }else throw new ParkingException("No Zone found");
    }

    @Override
    public PriceListDTO addPriceList(PriceListDTO priceListDTO, int zoneId) {
        Optional<ParkingZone> foundZone = parkingZoneRepo.findById(zoneId);
        if(foundZone.isPresent()){
            PriceList priceList = new PriceList(priceListDTO);
            return new PriceListDTO(priceListRepo.save(priceList));
        }else throw new ParkingException("No zone found");
        return null;
    }

    @Override
    public PriceScaleDTO addPriceScales() {
        return null;
    }

    @Override
    public void deleteScale(int scaleId) {

    }

    @Override
    public void deletePriceList(int priceListId) {

    }
}
