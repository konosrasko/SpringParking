package com.example.parking.entity;

import com.example.parking.dto.PriceListDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {
    @Test
    void DTOtoEntityTest(){
        PriceListDTO priceListDTO = new PriceListDTO();
        PriceList priceList = PriceList.builder().priceScales(new ArrayList<>()).build();
        assertEquals(
                priceList,
                new PriceList(null,priceListDTO)
        );
    }
}