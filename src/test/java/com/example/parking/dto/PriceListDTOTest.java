package com.example.parking.dto;

import com.example.parking.entity.PriceList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PriceListDTOTest {
    @Test
    void entityToDTO(){
        PriceListDTO priceListDTO = PriceListDTO.builder().priceScaleDTOList(new ArrayList<>()).build();
        PriceList priceList = PriceList.builder().priceScales(new ArrayList<>()).build();
        assertEquals(
                priceListDTO,
                new PriceListDTO(priceList)
        );
    }
}