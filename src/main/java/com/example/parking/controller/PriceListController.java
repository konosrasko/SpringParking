package com.example.parking.controller;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PriceListController {
    @Autowired
    private PriceListService priceListService;

    @GetMapping("/parking-zone/{zoneId}/priceList")
    List<PriceListDTO> getPriceListsOfZone(@PathVariable int zoneId){
        return null;
    }

    @PostMapping("/parking-zone/{zoneId}/priceList")
    public PriceListDTO addPriceList(@RequestBody PriceListDTO priceListDTO, @PathVariable int zoneId){
        return null;
    }

    @PostMapping("/pricelist/{priceListId}/priceScale")
    public void addPriceScale(@RequestBody PriceScaleDTO priceScaleDTO, @PathVariable int priceListId){
        priceListService.addPriceScales(priceListId,priceScaleDTO);
    }
}
