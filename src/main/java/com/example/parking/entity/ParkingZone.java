package com.example.parking.entity;

import com.example.parking.dto.ParkingZoneDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "parking_zone")
public class ParkingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "park_id", referencedColumnName = "id")
    private Parking parking;

    @OneToMany(mappedBy = "parkingZone",cascade = CascadeType.ALL)
    private List<PriceList> priceLists= new ArrayList<>();

    public ParkingZone(Parking parking, ParkingZoneDTO parkingZoneDTO) {
        this.id = parkingZoneDTO.getParkingZoneId();
        this.name = parkingZoneDTO.getName();
        this.type = parkingZoneDTO.getType();
        this.parking = parking;
        if(parkingZoneDTO.getParkingSpotDTOList() == null){
            this.parkingSpots = new ArrayList<>();
        }else {
            this.parkingSpots = parkingZoneDTO.getParkingSpotDTOList()
                .stream()
                .map(parkingSpotDTO -> new ParkingSpot(this,parkingSpotDTO))
                .toList();
        }
    }
    public void addPriceList(PriceList priceList){
        this.priceLists.add(priceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getName(), getParkingSpots(), parking);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingZone that)) return false;
        return getId() == that.getId() && Objects.equals(getType(), that.getType()) && Objects.equals(getName(), that.getName()) && Objects.equals(getParkingSpots(), that.getParkingSpots());
    }

    public float zoneCapacityPercentage() {
        int occupiedSpots = (int) parkingSpots.stream()
                .filter(ParkingSpot::isOccupied)
                .count();
        if(parkingSpots.size() > 0){
            return (float) (occupiedSpots*100 / parkingSpots.size());
        }else {
            return 0;
        }

    }

    public  List<ParkingSpot> findEmptySpots(){
        return parkingSpots.stream()
                .filter(parkingSpot -> !parkingSpot.isOccupied())
                .toList();
    }
}
