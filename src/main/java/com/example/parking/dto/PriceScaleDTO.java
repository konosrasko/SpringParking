package com.example.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceScaleDTO {
    private int priceScaleId;
    private double scaleDuration;
    private double scalePerTimeUnit;
    private double scaleCost;


}
