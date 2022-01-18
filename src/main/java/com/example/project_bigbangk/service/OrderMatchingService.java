// Created by Deek
// Creation date 1/18/2022

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderMatchingService {

    RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderMatchingService.class);

    public OrderMatchingService(RootRepository rootRepository) {
        super();
        logger.info("New OrderMatchingService");
        this.rootRepository = rootRepository;
    }
    public void checkForMatchingOrders(){
        //get all orders
    }
}