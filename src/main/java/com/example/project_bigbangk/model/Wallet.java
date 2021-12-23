// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.model;

import com.example.project_bigbangk.model.Orders.Limit_Buy;
import com.example.project_bigbangk.model.Orders.Limit_Sell;
import com.example.project_bigbangk.model.Orders.Stoploss_Sell;
import com.example.project_bigbangk.model.Orders.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Wallet {

    private String bank;
    private String iban;
    private double balance;
    private Map<Asset, Double> asset;
    private List<Transaction> transaction;
    private List<Limit_Sell> limitSell;
    private List<Limit_Buy> limitBuy;
    private List<Stoploss_Sell> stoplossSell;
    private ICanTrade owner;

    //Is het niet handiger om het type er achter te zetten zoals buyOrderList of assetMap, transactionList?

    public Wallet(String bank, String iban, double balance, Map<Asset, Double> asset, List<Transaction> transaction,
                  List<Limit_Sell> limitSell, List<Limit_Buy> limnitBuy, List<Stoploss_Sell> stoplossSell,
                  ICanTrade owner) {
        this.bank = bank;
        this.iban = iban;
        this.balance = balance;
        this.asset = asset;
        this.transaction = transaction;
        this.limitSell = limitSell;
        this.limitBuy = limnitBuy;
        this.stoplossSell = stoplossSell;
        this.owner = owner;
    }

    public Wallet(String iban, double balance) {
        this.iban = iban;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "bank='" + bank + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", assets=" + asset +
                ", transactions=" + transaction +
                ", limitSell=" + limitSell +
                ", limitBuy=" + limitBuy +
                ", stoplossSell=" + stoplossSell +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.balance, balance) == 0 && iban.equals(wallet.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balance);
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

    public List<Limit_Sell> getLimitSell() {
        return limitSell;
    }

    public void setLimitSell(List<Limit_Sell> limitSell) {
        this.limitSell = limitSell;
    }

    public List<Limit_Buy> getLimitBuy() {
        return limitBuy;
    }

    public void setLimitBuy(List<Limit_Buy> limitBuy) {
        this.limitBuy = limitBuy;
    }

    public List<Stoploss_Sell> getStoplossSell() {
        return stoplossSell;
    }

    public void setStoplossSell(List<Stoploss_Sell> stoplossSell) {
        this.stoplossSell = stoplossSell;
    }

    public ICanTrade getOwner() {
        return owner;
    }

    public void setOwner(ICanTrade owner) {
        this.owner = owner;
    }
}