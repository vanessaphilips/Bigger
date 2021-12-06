package com.example.project_bigbangk.model;

import java.util.List;

public class Bank {
    private Wallet wallet;
    private List<Client> clients;
    private List<Asset> offeredCryptos;
    //private List<Administrator> administrators;
    //private User bankUser;  // Bank direct aan User koppelen of wel voor een administrator gaan?
    private String name;
    private String code;
    private double feePercentage;

    public Bank(String name, String code, double feePercentage) {
        this.name = name;
        this.code = code;
        this.feePercentage = feePercentage;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "wallet=" + wallet +
                ", clients=" + clients +
                ", offeredCryptos=" + offeredCryptos +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", feePercentage=" + feePercentage +
                '}';
    }

    // Hieronder staan alle nodige getters en setters
    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Asset> getOfferedCryptos() {
        return offeredCryptos;
    }

    public void setOfferedCryptos(List<Asset> offeredCryptos) {
        this.offeredCryptos = offeredCryptos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getFeePercentage() {
        return feePercentage;
    }

    public void setFeePercentage(double feePercentage) {
        this.feePercentage = feePercentage;
    }
}
