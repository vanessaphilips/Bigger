package com.example.project_bigbangk.service;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

import com.example.project_bigbangk.model.DTO.OrderDTO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;

@Service
public class Orderservice {

    private RootRepository rootRepository;


    public Orderservice(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public void executeOrderByType(OrderDTO order){
        //if type = x y z bla bla, stuur naar andere methode.

    }



}
