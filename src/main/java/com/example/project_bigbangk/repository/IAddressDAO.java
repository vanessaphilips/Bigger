package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;

import java.util.List;

public interface IAddressDAO {

    public void save(Address mpAddress);

    public Address findAddressByPostalcodeNumber(String postalcode, int number);

    public List<Address> findAllAddresses();

    public void update(Address address);

    public List<Address> findAddressByPostalcode(String postalcode);

}
