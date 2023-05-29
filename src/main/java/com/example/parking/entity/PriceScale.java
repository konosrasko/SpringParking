package com.example.parking.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_list_id", referencedColumnName = "id")
    private PriceList priceList;

}
