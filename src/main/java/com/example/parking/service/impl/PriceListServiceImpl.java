package com.example.parking.service.impl;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.entity.PriceList;
import com.example.parking.entity.PriceScale;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.repository.PriceListRepo;
import com.example.parking.repository.PriceScaleRepo;
import com.example.parking.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PriceListServiceImpl implements PriceListService {
    @Autowired
    private PriceListRepo priceListRepo;
    @Autowired
    private PriceScaleRepo priceScaleRepo;
    @Autowired
    ParkingZoneRepo parkingZoneRepo;

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
    public PriceScaleDTO addPriceScales(int priceListId, PriceScaleDTO priceScaleDTO) {
        Optional<PriceList> foundPriceList = priceListRepo.findById(priceListId);
        if(foundPriceList.isPresent()){
            PriceScale priceScale = new PriceScale(foundPriceList.get(),priceScaleDTO);
            return new PriceScaleDTO(priceScaleRepo.save(priceScale));
        }else{
            throw new ParkingException("the PriceList doesnt exist");
        }
    }

    @Override
    public void deleteScale(int scaleId) {
        Optional<PriceScale> priceList = priceScaleRepo.findById(scaleId);
        if (priceList.isPresent()) {
            priceScaleRepo.deleteById(scaleId);
        } else {
            throw new RuntimeException("Price scale with id : " + scaleId + " does not exist");
        }
    }

    @Override
    public void deletePriceList(int priceListId) {
        Optional<PriceList> priceList = priceListRepo.findById(priceListId);
        if (priceList.isPresent()) {
            priceListRepo.deleteById(priceListId);
        } else {
            throw new RuntimeException("Price list with id : " + priceListId + " does not exist");
        }
    }
}