package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Object created by Vanessa Philips.
 * Abstract class for "Order/Transaction".
 */

abstract class AbstractOrder {

    private long orderId;
    private Asset asset;
    private double requestedPrice;
    private int numberOfAssets;
    private LocalDateTime date;

    public AbstractOrder(long orderId, Asset asset, double requestedPrice, int numberOfAssets, LocalDateTime date) {
        this.orderId = orderId;
        this.asset = asset;
        this.requestedPrice = requestedPrice;
        this.numberOfAssets = numberOfAssets;
        this.date = date;
    }

    public AbstractOrder(long orderId, double requestedPrice, int numberOfAssets, LocalDateTime date) {
        this(orderId, null, requestedPrice, numberOfAssets, date);
    }

    public AbstractOrder(Asset asset, double requestedPrice, int numberOfAssets, LocalDateTime date) {
        this(0, asset, requestedPrice, numberOfAssets, date);
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

    public double getRequestedPrice() {
        return requestedPrice;
    }

    public void setRequestedPrice(double requestedPrice) {
        this.requestedPrice = requestedPrice;
    }

    public int getNumberOfAssets() {
        return numberOfAssets;
    }

    public void setNumberOfAssets(int numberOfAssets) {
        this.numberOfAssets = numberOfAssets;
    }

    public LocalDateTime getDate(Class<LocalDateTime> localDateTimeClass) {
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
                ", requestedPrice=" + requestedPrice +
                ", numberOfAssets=" + numberOfAssets +
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