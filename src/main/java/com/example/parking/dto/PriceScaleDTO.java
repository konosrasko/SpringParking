package com.example.parking.dto;

import com.example.parking.entity.PriceScale;
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
    public PriceScaleDTO(PriceScale priceScale) {
        this.priceScaleId = priceScale.getId();
        this.scaleDuration = priceScale.getScaleDuration();
        this.scalePerTimeUnit = priceScale.getScalePerTimeUnit();
        this.scaleCost = priceScale.getScaleCost();
    }


}
