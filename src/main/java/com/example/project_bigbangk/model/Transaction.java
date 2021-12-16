package com.example.project_bigbangk.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Model created by Vanessa Philips.
 * Object "Transaction" for "regular" transactions.
 */

public class Transaction extends AbstractOrder{
    private Wallet buyerWallet;
    private Wallet sellerWallet;

    public Transaction(int orderId, Asset asset, double requestedPrice, int numberOfAssets,
                       LocalDateTime date, double transactionFee, Wallet buyerWallet, Wallet sellerWallet) {
        super(orderId, asset, requestedPrice, numberOfAssets, date, transactionFee);
        this.buyerWallet = buyerWallet;
        this.sellerWallet = sellerWallet;
    }

    // TODO alle getters en setters aangemaakt. Als blijkt dat eea niet gebruikt wordt dan weghalen.

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(buyerWallet, that.buyerWallet) && Objects.equals(sellerWallet, that.sellerWallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), buyerWallet, sellerWallet);
    }
}
