package com.example.parking.service;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.entity.PriceList;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PriceListService {

    PriceList savePriceList(PriceListDTO priceListDTO);
    List<PriceListDTO> findAllPriceLists();

    List<PriceListDTO> findPriceListByZoneId(int zoneId);
}
