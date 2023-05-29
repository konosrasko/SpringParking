package com.example.parking.controller;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.entity.PriceList;
import com.example.parking.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PriceListController {
    @Autowired
    private PriceListService priceListService;

    @PostMapping("/pricelist")
    public PriceListDTO addPriceList(){
        return null;
    }
}
