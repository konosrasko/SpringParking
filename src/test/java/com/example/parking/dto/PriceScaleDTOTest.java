package com.example.parking.dto;

import com.example.parking.entity.PriceScale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceScaleDTOTest {
    @Test
    void EntityToDTOTest(){
        PriceScale priceScale = new PriceScale();
        PriceScaleDTO priceScaleDTO = new PriceScaleDTO();
        assertEquals(priceScaleDTO,new PriceScaleDTO(priceScale));
    }
}