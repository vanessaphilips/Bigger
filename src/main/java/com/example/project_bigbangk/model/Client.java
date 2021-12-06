// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.model;

// Client object.

import java.util.Date;
import java.util.Objects;

public class Client {
    private String firstName;
    private String insertion;
    private String lastName;
    private String email;
    private String bsn;
    private Date dateOfBirth; //FIXME dit moet een LocalDate worden ipv Date, ook in de DAO (was achteraf iets handiger dan Date)
    private String passWord;
    private Address address;
    private Wallet wallet;

    public Client(String firstName, String insertion, String lastName, String email,
                  String bsn, Date dateOfBirth, String passWord, Address address, Wallet wallet) {
        this.firstName = firstName;
        this.insertion = insertion;
        this.lastName = lastName;
        this.email = email;
        this.bsn = bsn;
        this.dateOfBirth = dateOfBirth;
        this.passWord = passWord;
        this.address = address;
        this.wallet = wallet;
    }

    // TODO alle getters en setters aangemaakt. Als blijkt dat eea niet gebruikt wordt dan weghalen.

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInsertion() {
        return insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", insertion='" + insertion + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", bsn='" + bsn + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passWord='" + passWord + '\'' +
                ", address=" + address +
                ", wallet=" + wallet +
                '}';
    }

    //FIXME alleen comparison van email nodig om gebruikers te vergelijken
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(insertion, client.insertion) &&
                Objects.equals(lastName, client.lastName) && Objects.equals(email, client.email) &&
                Objects.equals(bsn, client.bsn) && Objects.equals(dateOfBirth, client.dateOfBirth) &&
                Objects.equals(passWord, client.passWord) && Objects.equals(address, client.address) &&
                Objects.equals(wallet, client.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, insertion, lastName, email, bsn, dateOfBirth, passWord, address, wallet);
    }
}