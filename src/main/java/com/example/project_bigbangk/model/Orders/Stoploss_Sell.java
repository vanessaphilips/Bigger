package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Model created by Vanessa Philips.
 * Object "Stoploss_Sell" > Sell (trigger)transaction if price reaches a certain level below the current price.
 */

public class Stoploss_Sell extends AbstractOrder{

    private final Logger logger = LoggerFactory.getLogger(Stoploss_Sell.class);

    private Wallet sellerWallet;
    private double lowerBound;
    private double transactionFee;

    public Stoploss_Sell(int orderId, double requestedPrice, int numberOfAssets, LocalDateTime date,
                         double transactionFee, double lowerBound) {
        super(orderId, requestedPrice, numberOfAssets, date);
        this.transactionFee = transactionFee;
        this.lowerBound = lowerBound;
        logger.info("New Stoploss_Sell, without Asset and Wallet");
    }

    public Stoploss_Sell(Asset asset, double requestedPrice, int numberOfAssets, LocalDateTime date, Wallet sellerWallet, double lowerBound, double transactionFee) {
        super(asset, requestedPrice, numberOfAssets, date);
        this.sellerWallet = sellerWallet;
        this.lowerBound = lowerBound;
        this.transactionFee = transactionFee;
        logger.info("New Stoploss_Sell, without id");
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Wallet getSellerWallet() {
        return sellerWallet;
    }

    public void setSellerWallet(Wallet sellerWallet) {
        this.sellerWallet = sellerWallet;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Stoploss_Sell{" +
                "sellerWallet=" + sellerWallet +
                ", lowerBound=" + lowerBound +
                ", transactionFee=" + transactionFee +
                '}';
    }
}