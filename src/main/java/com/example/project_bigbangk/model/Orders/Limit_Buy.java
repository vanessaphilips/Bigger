package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Model created by Vanessa Philips.
 * Object "Limit_Buy" > Buy transaction if the price reaches below a desired level.
 */

public class Limit_Buy extends AbstractOrder{

    private final Logger logger = LoggerFactory.getLogger(Limit_Buy.class);

    private double transactionFee;
    private Wallet buyerWallet;

    public Limit_Buy(int orderId, double requestedPrice, int numberOfAssets,
                     LocalDateTime date, double transactionFee) {
        super(orderId, requestedPrice, numberOfAssets, date);
        this.transactionFee = transactionFee;
        logger.info("New Limit_Buy, without Asset and Wallet");
    }

    public Limit_Buy(Asset asset, double requestedPrice, int numberOfAssets, LocalDateTime date, double transactionFee, Wallet buyerWallet) {
        super(asset, requestedPrice, numberOfAssets, date);
        this.transactionFee = transactionFee;
        this.buyerWallet = buyerWallet;
        logger.info("New Limit_Buy, without id");
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Wallet getBuyerWallet() {
        return buyerWallet;
    }

    public void setBuyerWallet(Wallet buyerWallet) {
        this.buyerWallet = buyerWallet;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Limit_Buy{" +
                "transactionFee=" + transactionFee +
                ", buyerWallet=" + buyerWallet +
                '}';
    }
}