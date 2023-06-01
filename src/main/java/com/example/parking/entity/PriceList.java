package com.example.parking.entity;

import com.example.parking.dto.PriceListDTO;
import com.example.parking.dto.PriceScaleDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode

@Entity
@Table(name="price_list")
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="date_start")
    private OffsetDateTime dateStart;

    @Column(name="date_end")
    private OffsetDateTime dateEnd;

    @Column(name="type")
    private String type;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    private ParkingZone parkingZone;

    @OneToMany(mappedBy = "priceList", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceScale> priceScales;

    public PriceList(ParkingZone zone, PriceListDTO priceListDTO){
        this.id = priceListDTO.getPriceListId();
        this.dateStart = priceListDTO.getDateStart();
        this.dateEnd = priceListDTO.getDateEnd();
        this.type = priceListDTO.getType();
        this.parkingZone = zone;
        if(priceListDTO.getPriceScaleDTOList() == null){
            this.priceScales = new ArrayList<>();
        }else{
            this.priceScales = priceListDTO.getPriceScaleDTOList()
                    .stream()
                    .map(priceScaleDTO -> new PriceScale(this, priceScaleDTO))
                    .toList();
        }
    }

    public double totalCost(long duration){

        double totalCost=0;
        int i=0;
        int scaleListSize = priceScales.size() - 1;

        while (duration > 0){
            if(duration > priceScales.get(scaleListSize).getScaleDuration()){
                totalCost += priceScales.get(scaleListSize).getScaleCost() * (priceScales.get(scaleListSize).getScaleDuration()/priceScales.get(scaleListSize).getScalePerTimeUnit()) ;
                duration -= priceScales.get(scaleListSize).getScaleDuration();
            }else{
                if(duration>=priceScales.get(i).getScaleDuration()) {
                    totalCost += priceScales.get(i).getScaleCost() * (priceScales.get(i).getScaleDuration()/priceScales.get(i).getScalePerTimeUnit()) ;
                    duration -= priceScales.get(i).getScaleDuration();
                    i++;
                }else{
                    totalCost += priceScales.get(i).getScaleCost() * ((duration/priceScales.get(i).getScalePerTimeUnit())) ;
                    duration=0;
                }
            }
        }

        System.out.println("Your total cost is: "+totalCost);
        return totalCost;

    }
}
