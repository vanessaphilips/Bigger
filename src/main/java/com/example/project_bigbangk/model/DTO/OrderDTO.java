package com.example.project_bigbangk.model.DTO;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

public class OrderDTO {

    private String assetCode; //automatisch ingevuld veld.
    private String orderType; //dropdown menu of zoiets.
    private double limit; //de trigger voor alle orders behalve transacties met bank. Sprint 3 default 0 of -1 bij transacties? (BUY SELL als type)
    private double assetAmount; //hoeveelheid coin of hoeveelheid geld(alleen in het geval dat je van de bank koopt. dan koop je 100€ aan bitcoin ipv 0.0004 bitcoin.

    public OrderDTO(String assetCode, String orderType, double limit, double assetAmount) {
        this.assetCode = assetCode;
        this.orderType = orderType;
        this.limit = limit;
        this.assetAmount = assetAmount;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(double assetAmount) {
        this.assetAmount = assetAmount;
    }





}
