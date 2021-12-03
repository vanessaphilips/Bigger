// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.model;

import java.util.List;
import java.util.Map;

public class Wallet {

    private String bank;
    private String iban;
    private double balance;
    private Map<Asset, Double> asset;
    private List<Transaction> transaction;
    private List<SellOrder> sellOrder;
    private List<BuyOrder> buyOrder;
    private List<PendingOrder> pendingOrder;
    private ICanTrade owner;

    //Is het niet handiger om het type er achter te zetten zoals buyOrderList of assetMap, transactionList?

    public Wallet(String bank, String iban, double balance, Map<Asset, Double> asset, List<Transaction> transaction,
                  List<SellOrder> sellOrder, List<BuyOrder> buyOrder, List<PendingOrder> pendingOrder,
                  ICanTrade owner) {
        this.bank = bank;
        this.iban = iban;
        this.balance = balance;
        this.asset = asset;
        this.transaction = transaction;
        this.sellOrder = sellOrder;
        this.buyOrder = buyOrder;
        this.pendingOrder = pendingOrder;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "bank='" + bank + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", assets=" + asset +
                ", transactions=" + transaction +
                ", sellOrders=" + sellOrder +
                ", buyOrders=" + buyOrder +
                ", pendingOrders=" + pendingOrder +
                ", owner=" + owner +
                '}';
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<Asset, Double> getAsset() {
        return asset;
    }

    public void setAsset(Map<Asset, Double> asset) {
        this.asset = asset;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public List<SellOrder> getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(List<SellOrder> sellOrder) {
        this.sellOrder = sellOrder;
    }

    public List<BuyOrder> getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(List<BuyOrder> buyOrder) {
        this.buyOrder = buyOrder;
    }

    public List<PendingOrder> getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(List<PendingOrder> pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public ICanTrade getOwner() {
        return owner;
    }

    public void setOwner(ICanTrade owner) {
        this.owner = owner;
    }
}