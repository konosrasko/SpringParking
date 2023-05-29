package com.example.parking.controller;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import com.example.parking.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PriceListController {
    @Autowired
    private PriceListService priceListService;

    @PostMapping("/pricelist")
    public PriceListDTO addPriceList(){
        return null;
    }
    @PostMapping("/pricelist/{id}/priceScale")
    public void addPriceScale(@RequestBody PriceScaleDTO priceScaleDTO, @PathVariable int priceListId){
        priceListService.addPriceScales(priceListId,priceScaleDTO);
    }
}
