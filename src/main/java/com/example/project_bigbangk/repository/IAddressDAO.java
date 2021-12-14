package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;

import java.util.List;

public interface IAddressDAO {

    public void saveAddress(Address mpAddress);

    public Address findAddressByEmail(String email);

    public List<Address> findAllAddresses();

    public List<Address> findAddressByPostalcode(String postalcode);

}
