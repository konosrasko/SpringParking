package com.example.parking.service.impl;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.entity.PriceList;
import com.example.parking.entity.PriceScale;
import com.example.parking.repository.ParkingZoneRepo;
import com.example.parking.repository.PriceListRepo;
import com.example.parking.repository.PriceScaleRepo;
import com.example.parking.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PriceListServiceImpl implements PriceListService {
    @Autowired
    ParkingZoneRepo parkingZoneRepo;

    @Autowired
    PriceListRepo priceListRepo;

    @Autowired
    PriceScaleRepo priceScaleRepo;

    @Override
    public PriceListDTO addPriceList() {
        return null;
    }

    @Override
    public PriceListDTO getPriceList(int zoneId) {
        return null;
    }

    @Override
    public PriceScaleDTO addPriceScales() {
        return null;
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
