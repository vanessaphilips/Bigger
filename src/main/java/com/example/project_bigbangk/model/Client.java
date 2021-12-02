package com.example.project_bigbangk.model;

// Client object.

import java.util.Date;
import java.util.Objects;

public class Client {
    private String firstName;
    private String insertion;
    private String lastName;
    private String email;
    private String bsnNumber;
    private Date dateOfBirth;
    private String password;
    private String salt;
    private Address adres;

    public Client(String firstName, String insertion, String lastName, String email,
                  String bsnNumber, Date dateOfBirth, String password, String salt, Address adres) {
        this.firstName = firstName;
        this.insertion = insertion;
        this.lastName = lastName;
        this.email = email;
        this.bsnNumber = bsnNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.salt = salt;
        this.adres = adres;
    }

    // TODO alle getters en setters aangemaakt als uiteindelijk blijkt dat ze niet gebruikt worden dan weghalen

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

    public String getBsnNumber() {
        return bsnNumber;
    }

    public void setBsnNumber(String bsnNumber) {
        this.bsnNumber = bsnNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Address getAdres() {
        return adres;
    }

    public void setAdres(Address adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", insertion='" + insertion + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", bsnNumber='" + bsnNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", adres=" + adres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(insertion, client.insertion) &&
                Objects.equals(lastName, client.lastName) && Objects.equals(email, client.email) &&
                Objects.equals(bsnNumber, client.bsnNumber) && Objects.equals(dateOfBirth, client.dateOfBirth) &&
                Objects.equals(password, client.password) && Objects.equals(salt, client.salt) &&
                Objects.equals(adres, client.adres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, insertion, lastName, email, bsnNumber, dateOfBirth, password, salt, adres);
    }
}