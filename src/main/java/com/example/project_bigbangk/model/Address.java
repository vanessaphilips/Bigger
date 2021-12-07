package com.example.project_bigbangk.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Address {

    private String postalCode;
    private String street;
    private int number;
    private String city;
    private String country;

    public Address(String postalCode, String street, int number, String city, String country) {
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return String.format("[Postalcode %s housenumber %s]", this.postalCode, this.number);
    }

}