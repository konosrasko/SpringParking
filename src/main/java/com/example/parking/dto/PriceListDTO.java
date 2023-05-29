package com.example.parking.dto;

import com.example.parking.entity.PriceScale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceListDTO {
    private int priceListId;
    private OffsetDateTime dateStart;
    private OffsetDateTime dateEnd;
    private String type;
    private List<PriceScale> priceScales;



}
