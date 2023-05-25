package com.example.parking.entity;

import com.example.parking.dto.ParkingOccupationDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "parking_history")
public class ParkingOccupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_id")
    private ParkingSpot parkingSpot;
    @Column(name = "date_start")
    private OffsetDateTime occupationDate;
    @Column(name = "date_end")
    private OffsetDateTime vacancyDate;
    @Column(name = "total_cost")
    private float cost;
    @Column(name = "vehicle_plate")
    private String plate;



    public ParkingOccupation(ParkingOccupationDTO parkingOccupationDTO){
        this.occupationDate = parkingOccupationDTO.getOccupationDate();
        this.vacancyDate = parkingOccupationDTO.getVacancyDate();
        this.cost = parkingOccupationDTO.getCost();
        this.plate = parkingOccupationDTO.getPlate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingOccupation that)) return false;
        return Float.compare(that.getCost(), getCost()) == 0 && Objects.equals(getParkingSpot(), that.getParkingSpot()) && Objects.equals(getOccupationDate(), that.getOccupationDate()) && Objects.equals(getVacancyDate(), that.getVacancyDate()) && Objects.equals(getPlate(), that.getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingSpot(), getOccupationDate(), getVacancyDate(), getCost(), getPlate());
    }
}
