package com.example.project_bigbangk.model;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

import java.time.LocalDate;
import java.util.Date;


public class RegistrationDTO {

    private String email;
    private String password;
    private String firstName;
    private String insertion;
    private String lastName;
    private String bsnNumber;
    private LocalDate dateOfBirth;
    private String postalCode;
    private String street;
    private int number;
    private String city;
    private String country;

    public RegistrationDTO(String email, String password, String firstName, String insertion, String lastName,
                           String bsnNumber, LocalDate dateOfBirth, String postalCode, String street, int number, String city, String country) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.insertion = insertion;
        this.lastName = lastName;
        this.bsnNumber = bsnNumber;
        this.dateOfBirth = dateOfBirth;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getInsertion() {
        return insertion;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBsnNumber() {
        return bsnNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
