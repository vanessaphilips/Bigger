package com.example.project_bigbangk.model;

import java.util.List;
import java.util.Objects;

/**
 * Hier staat alle informatie waar een bank uit bestaat.
 *
 * @Author Kelly Speelman - de Jonge
 */

public class Bank {
    private Wallet wallet;
    private List<Client> clients;
    private List<Asset> offeredCryptos;
    private String name;
    private String code;
    private double feePercentage;
    private double startingcapital;

    public Bank(String name, String code, double feePercentage, double startingcapital) {
        this.name = name;
        this.code = code;
        this.feePercentage = feePercentage;
        this.startingcapital = startingcapital;
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
                ", startingcapital='" + startingcapital + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return name.equals(bank.name) && code.equals(bank.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
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

    public double getStartingcapital() {
        return startingcapital;
    }

    public void setStartingcapital(double startingcapital) {
        this.startingcapital = startingcapital;
    }
}
