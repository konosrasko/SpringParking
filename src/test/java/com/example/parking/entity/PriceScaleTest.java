package com.example.parking.entity;

import com.example.parking.dto.PriceScaleDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceScaleTest {
    @Test
    void DTOtoEntityTest(){
        PriceScale priceScale = new PriceScale();
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO();
        assertEquals(priceScale,new PriceScale(null,priceScaleDTO));
    }
}