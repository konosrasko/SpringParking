package com.example.parking.entity;

import com.example.parking.dto.PriceScaleDTO;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode

@Entity
@Table(name="price_scale")
public class PriceScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="duration_of_price_scale")
    private double scaleDuration;

    @Column(name ="scale")
    private double scalePerTimeUnit;

    @Column(name="cost")
    private double scaleCost;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_list_id", referencedColumnName = "id")
    private PriceList priceList;

    public PriceScale(PriceList priceList, PriceScaleDTO priceScaleDTO) {
        this.priceList=priceList;
        id=priceScaleDTO.getPriceScaleId();
        scaleDuration= priceScaleDTO.getScaleDuration();
        scalePerTimeUnit = priceScaleDTO.getScalePerTimeUnit();
        scaleCost=priceScaleDTO.getScaleCost();
    }
}
