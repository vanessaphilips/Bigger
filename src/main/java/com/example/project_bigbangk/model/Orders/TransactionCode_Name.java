// Created by vip
// Creation date 16/12/2021

package com.example.project_bigbangk.model.Orders;

/**
 * Enum created by Vanessa Philips.
 * 4 types of transactions/orders.
 */

public enum TransactionCode_Name {
    TRANSACTION ("Transaction"),
    LIMIT_SELL ("Sell if price reaches level above current level"),
    LIMIT_BUY("Buy if price reaches below desired level"),
    STOPLOSS_SELL("Sell if price reaches certain level below current price");

    private String transactionName;

    private TransactionCode_Name(String transactionName) {
        this.transactionName =  transactionName;
    }

    @Override
    public String toString() {
        return transactionName;
    }

    public String getTransactionCode() {
        return this.name();
    }

    public String getTransactionName() {
        return transactionName;
    }
}