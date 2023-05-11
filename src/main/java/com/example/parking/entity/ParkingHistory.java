package com.example.parking.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "parking_history")
public class ParkingHistory {

    @ManyToOne
    @JoinColumn(name = "park_id")
    private Parking parking;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private ParkingZone parkingZone;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private ParkingSpot parkingSpot;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "total_cost")
    private  double totalCost;

    @Column(name = "vehicle_plate")
    private String vehiclePlate;

    public ParkingHistory() {
    }

    public ParkingHistory(Parking parking, ParkingZone parkingZone, ParkingSpot parkingSpot, Date dateStart, Date dateEnd, double totalCost, String vehiclePlate) {
        this.parking = parking;
        this.parkingZone = parkingZone;
        this.parkingSpot = parkingSpot;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.totalCost = totalCost;
        this.vehiclePlate = vehiclePlate;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public ParkingZone getParkingZone() {
        return parkingZone;
    }

    public void setParkingZone(ParkingZone parkingZone) {
        this.parkingZone = parkingZone;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }
}
