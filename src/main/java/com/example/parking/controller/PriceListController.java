package com.example.parking.controller;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.service.impl.PriceListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PriceListController {
    private final PriceListServiceImpl priceListServiceImpl;

    @Autowired
    public PriceListController(PriceListServiceImpl priceListService) {
        this.priceListServiceImpl = priceListService;
    }

    @GetMapping("/pricelist")
    public List<PriceListDTO> getAllPriceLists(){
        return priceListServiceImpl.findAllPriceLists();
    }
}
