package com.example.parking.service.impl;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.entity.PriceList;
import com.example.parking.exception.ParkingException;
import com.example.parking.repository.PriceListRepo;
import com.example.parking.service.PriceListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepo priceListRepo;

    public PriceListServiceImpl(PriceListRepo priceListRepo) {
        this.priceListRepo = priceListRepo;
    }


    @Override
    public PriceList savePriceList(PriceListDTO priceListDTO) {
        Optional<PriceList> priceListByIdResult = priceListRepo.findById(priceListDTO.getPriceListId());

        if (priceListByIdResult.isPresent()){
            throw new ParkingException("priceList with this id exists");
        }

        PriceList priceList = new PriceList();
        priceList.setId(priceListDTO.getPriceListId());
        priceList.setType(priceListDTO.getType());
        priceList.setDateStart(priceListDTO.getDateStart());
        priceList.setDateEnd(priceListDTO.getDateEnd());

        return priceListRepo.save(priceList);
    }

    @Override
    public List<PriceListDTO> findAllPriceLists() {

        return priceListRepo.findAll()
                .stream()
                .map(priceList -> new PriceListDTO(
                        priceList.getId(),
                        priceList.getDateStart(),
                        priceList.getDateEnd(),
                        priceList.getType()
                )).collect(Collectors.toList());
    }

    @Override
    public List<PriceListDTO> findPriceListByZoneId(int zoneId) {

        List<PriceList> priceLists = this.priceListRepo.findByZoneId(zoneId);
        return priceLists
                .stream()
                .map(priceList -> new PriceListDTO(
                        priceList.getId(),
                        priceList.getDateStart(),
                        priceList.getDateEnd(),
                        priceList.getType()
                )).collect(Collectors.toList());
    }







}
