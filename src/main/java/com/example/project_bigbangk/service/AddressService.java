// Created by RayS
// Creation date 2-12-2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.repository.JdbcAddressDAO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private JdbcAddressDAO jdbcAddressDAO;
    private RootRepository rootRepository;

    public AddressService (JdbcAddressDAO jdbcAddressDAO, RootRepository rootRepository) {
        this.jdbcAddressDAO = jdbcAddressDAO;
        this.rootRepository = rootRepository;
    }

    public void saveAddress(Address address) { jdbcAddressDAO.saveAddress(address);}

    public Address getAddressByEmail (String email){ return rootRepository.findAddressByEmail(email); }

    public List<Address> getAllAddresses() {
        return jdbcAddressDAO.findAllAddresses();
    }

    public List<Address> getAddressByPostalcode (String postalCode) {
        return jdbcAddressDAO.findAddressByPostalcode(postalCode);
    }




}