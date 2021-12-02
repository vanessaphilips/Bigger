// Created by RayS
// Creation date 2-12-2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.repository.AddressDAO;
import com.example.project_bigbangk.repository.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressDAO addressDAO;

    @Autowired
    public AddressService (AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void saveAddress(Address address) { addressDAO.save(address);}

    public List<Address> findAllAddresses() {
        return addressDAO.findAllAddresses();
    }
//    TODO even nagaan deze, want 1. nog geen clientDAO ingericht en 2. moet deze hier of in rootdepository ivm opvragen gegevens
//    via client (immers gegevens gekoppeld aan client en dus ook update via client-input
//    public String updateKlant(Address address) {
//        if (clientDAO.findClientById(client.get()) == null ) {
//            return "This client does not exist, update failed.";
//        } else {
//            addressDAO.update(address);
//            return "Update successful";
//        }
//    }
    public List<Address> findAddressByPostalcodeAndNumber (String postalCode, int number) {
        return addressDAO.findAddressByPostalcodeAndNumber(postalCode, number);
    }




}