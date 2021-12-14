package com.example.project_bigbangk.model;

/**
 * Created by Karim Ajour on 3-12-2021 for Project_Big_Bangk
 */

public class Asset {

    private String name;
    private String code;
    private double currentPrice;

    public Asset(String name, String code, double currentPrice) {
        this.name = name;
        this.code = code;
        this.currentPrice = currentPrice;
    }
    public Asset(String name, String code) {
        this.name = name;
        this.code = code;

    }

    @Override
    public String toString() {
        return "Asset{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
