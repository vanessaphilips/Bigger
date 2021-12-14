package com.example.project_bigbangk.model.DTO;
/*

@Author Bigbangk
*/


import java.util.Objects;

// velden in DTO moeten overeenkomen met JSON! en omgekeerd
public class RegistrationDTO {

    private String email;
    private String password;
    private String firstName;
    private String insertion;
    private String lastName;
    private String bsn;
    private String dateOfBirth;
    private String postalCode;
    private String street;
    private int number;
    private String city;
    private String country;

    public RegistrationDTO(String email, String password, String firstName, String insertion, String lastName,
                           String bsn, String dateOfBirth, String postalCode, String street, int number, String city, String country) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.insertion = insertion;
        this.lastName = lastName;
        this.bsn = bsn;
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

    public String getBsn() {
        return bsn;
    }

    public String getDateOfBirth() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationDTO that = (RegistrationDTO) o;
        return number == that.number && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(insertion, that.insertion) && Objects.equals(lastName, that.lastName) && Objects.equals(bsn, that.bsn) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(postalCode, that.postalCode) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, firstName, insertion, lastName, bsn, dateOfBirth, postalCode, street, number, city, country);
    }
}
