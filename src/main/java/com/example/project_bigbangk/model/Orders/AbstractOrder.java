package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Object created by Vanessa Philips.
 * Abstract class for "Order/Transaction".
 */

public abstract class AbstractOrder {

    private long orderId;
    private Asset asset;
    private double limit;
    private double assetAmount;
    private LocalDateTime date;

    public AbstractOrder(long orderId, Asset asset, double requestedPrice, double numberOfAssets, LocalDateTime date) {
        this.orderId = orderId;
        this.asset = asset;
        this.limit = limit;
        this.assetAmount = assetAmount;
        this.date = date;
    }

    public AbstractOrder(long orderId, double limit, double assetAmount, LocalDateTime date) {
        this(orderId, null, limit, assetAmount, date);
    }

    public AbstractOrder(Asset asset, double limit, double assetAmount, LocalDateTime date) {
        this(0, asset, limit, assetAmount, date);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
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

    public void setAssetAmount(int assetAmount) {
        this.assetAmount = assetAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AbstractOrder{" +
                "orderId=" + orderId +
                ", asset=" + asset +
                ", requestedPrice=" + limit +
                ", numberOfAssets=" + assetAmount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractOrder that = (AbstractOrder) o;
        return orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}