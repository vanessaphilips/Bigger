package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Model created by Vanessa Philips.
 * Object "Transaction" > for "regular" transactions.
 */

public class Transaction{

    private final Logger logger = LoggerFactory.getLogger(Transaction.class);

    private Asset asset;
    private double requestedPrice;
    private double numberOfAssets;
    private LocalDateTime date;
    private double transactionFee;
    private Wallet buyerWallet;
    private Wallet sellerWallet;
    private long orderId;

    public Transaction(long orderId, double requestedPrice, double numberOfAssets,
                       LocalDateTime date, double transactionFee) {
        this.orderId = orderId;
        this.requestedPrice = requestedPrice;
        this.transactionFee = transactionFee;
        this.date = date;
        this.numberOfAssets = numberOfAssets;
        logger.info("New transaction, without Asset and Wallets");
    }

    public Transaction(Asset asset, double requestedPrice, double numberOfAssets, LocalDateTime date,
                       double transactionFee, Wallet buyerWallet, Wallet sellerWallet) {
        this.asset = asset;
        this.requestedPrice = requestedPrice;
        this.date = date;
        this.numberOfAssets = numberOfAssets;
        this.transactionFee = transactionFee;
        this.buyerWallet = buyerWallet;
        this.sellerWallet = sellerWallet;
        logger.info("New transaction, without id");
    }

    public Logger getLogger() {
        return logger;
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


    public double getNumberOfAssets() {
        return numberOfAssets;
    }

    public void setNumberOfAssets(double numberOfAssets) {
        this.numberOfAssets = numberOfAssets;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public Wallet getSellerWallet() {
        return sellerWallet;
    }

    public void setSellerWallet(Wallet sellerWallet) {
        this.sellerWallet = sellerWallet;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    //FIXME toString aanpassen (transaction extends niet meer van abstract order)

    @Override
    public String toString() {
        return super.toString() +
                "Transaction{" +
                "transactionFee=" + transactionFee +
                ", buyerWallet=" + buyerWallet +
                ", sellerWallet=" + sellerWallet +
                '}';
    }
}
