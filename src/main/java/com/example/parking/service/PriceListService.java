package com.example.parking.service;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.entity.PriceScale;
import java.util.List;

public interface PriceListService {
    PriceListDTO addPriceList(PriceListDTO priceListDTO, int zoneId);
    List<PriceListDTO> getPriceList(int zoneId);

    PriceScaleDTO addPriceScales();

    void deleteScale(int scaleId);
    void deletePriceList(int priceListId);


}
