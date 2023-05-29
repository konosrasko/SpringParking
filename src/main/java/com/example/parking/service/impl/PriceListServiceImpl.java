package com.example.parking.service.impl;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.entity.PriceList;
import com.example.parking.entity.PriceScale;
import com.example.parking.exception.ParkingException;
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

    @Override
    public PriceListDTO addPriceList() {
        return null;
    }

    @Override
    public PriceListDTO getPriceList(int zoneId) {
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

    }

    @Override
    public void deletePriceList(int priceListId) {

    }
}
