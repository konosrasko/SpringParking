package com.example.parking.dto;

import com.example.parking.entity.PriceList;
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
    private List<PriceScaleDTO> priceScaleDTOList;

    public PriceListDTO(PriceList priceList){
        this.priceListId = priceList.getId();
        this.dateStart = priceList.getDateStart();
        this.dateEnd = priceList.getDateEnd();
        this.type = priceList.getType();
        this.priceScaleDTOList = priceList.getPriceScales().stream()
                .map(PriceScaleDTO::new)
                .toList();
    }

}
