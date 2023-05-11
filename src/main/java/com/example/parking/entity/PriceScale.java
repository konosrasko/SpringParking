package com.example.parking.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "price_scale")
public class PriceScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "price_list_id")
    private PriceList priceList;

    @Column(name="duration_of_price_scale")
    private Date duration;

    @Column(name="scale")
    private int scale;

    @Column(name="cost")
    private double cost;

    public PriceScale() {
    }

    public PriceScale(int id, PriceList priceList, Date duration, int scale, double cost) {
        this.id = id;
        this.priceList = priceList;
        this.duration = duration;
        this.scale = scale;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
