package com.example.parking.service;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;

import java.util.List;

public interface PriceListService {
    PriceListDTO addPriceList(PriceListDTO priceListDTO, int zoneId);
    List<PriceListDTO> getPriceList(int zoneId);

    PriceScaleDTO addPriceScales(int priceListId,PriceScaleDTO priceScaleDTO);

    PriceScaleDTO deleteScale(int scaleId);
    PriceListDTO deletePriceList(int priceListId);


}
