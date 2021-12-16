package com.example.project_bigbangk.model;

/**
 * Created by Karim Ajour on 3-12-2021 for Project_Big_Bangk
 */

public class Asset {

    private AssetCode_Name assetCodeName;
    private double currentPrice;

    public Asset(AssetCode_Name assetCodeName, double currentPrice) {
        this.currentPrice = currentPrice;
        this.assetCodeName = assetCodeName;

    }
    public Asset(AssetCode_Name assetCodeName) {
        this.assetCodeName = assetCodeName;

    }

    @Override
    public String toString() {
        return String.format("%s (%s)", assetCodeName.getAssetCode(), assetCodeName.getAssetName());
    }

//    public String getName() {
//        return assetCodeName.getAssetName();
//    }
//
//    public String getCode() {
//        return assetCodeName.getAssetCode();
//    }

    public void setAssetCodeEnum(AssetCode_Name assetCodeName) {
        this.assetCodeName = assetCodeName;
    }
    public AssetCode_Name getAssetCodeName() {
       return assetCodeName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
