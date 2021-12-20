package com.example.project_bigbangk.model.DTO;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

public class OrderDTO {

    private String code; //automatisch ingevuld veld.
    private String type; //dropdown menu of zoiets.
    private double limit; //de trigger voor alle orders behalve transacties met bank. Sprint 3
    private double amount; //dit is de hoeveelheid coin

    public OrderDTO(String code, String type, double limit, double amount) {
        this.code = code;
        this.type = type;
        this.limit = limit;
        this.amount = amount; //hoeveelheid coin of hoeveelheid geld(alleen in het geval dat je van de bank koopt. dan koop je 100€ aan bitcoin ipv 0.0004 bitcoin.
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    //Tabel parameters:
    //orderid is autoinc.
    //(WalletID in db) buyer of seller ben je zelf afhankelijk van order. en de ander een bank of een klant.
    //
    //code
    //type
    //limit
    //amount

    //totalprice        berekend in db
    //fee               staat ergens opgeslagen..in de code?




}
