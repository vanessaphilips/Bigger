// Created by RayS
// Creation date 2-12-2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.repository.AddressDAO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressDAO addressDAO;
    private RootRepository rootRepository;

    @Autowired
    public AddressService (AddressDAO addressDAO, RootRepository rootRepository) {
        this.addressDAO = addressDAO;
        this.rootRepository = rootRepository;
    }

    public void saveAddress(Address address) { addressDAO.save(address);}

    public Address getAddressByEmail (String email){ return rootRepository.findAddressByEmail(email); }

    public List<Address> getAllAddresses() {
        return addressDAO.findAllAddresses();
    }
    //TODO: even nagaan deze, want 1. nog geen clientDAO ingericht en 2. moet deze hier of in rootdepository ivm opvragen gegevens
    // via client (immers gegevens gekoppeld aan client en dus ook update via client-input
    //FIXME moet nu helaas ook anders ivm email
    public String updateAddress(Address address) {
        if (addressDAO.findAddressByEmail(address.getEmail()) == null ) {
            return "This client does not exist, update failed.";
        } else {
            addressDAO.update(address);
            return "Update successful";
        }
    }

    public List<Address> getAddressByPostalcode (String postalCode) {
        return addressDAO.findAddressByPostalcode(postalCode);
    }




}