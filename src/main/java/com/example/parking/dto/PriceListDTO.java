package com.example.parking.dto;

import java.util.Date;

public class PriceListDTO {
    private int PriceListId;
    private Date dateStart;
    private Date dateEnd;
    private String type;


    public PriceListDTO(){}
    public PriceListDTO(int priceListId, Date dateStart, Date dateEnd, String type) {
        this.PriceListId = priceListId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
    }

    public int getPriceListId() {
        return PriceListId;
    }

    public void setPriceListId(int priceListId) {
        PriceListId = priceListId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
