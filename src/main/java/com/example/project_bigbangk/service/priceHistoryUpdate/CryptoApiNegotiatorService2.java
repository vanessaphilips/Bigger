// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;


public class CryptoApiNegotiatorService2 implements ICryptoApiNegotiatorService {

    private final Logger logger = LoggerFactory.getLogger(CryptoApiNegotiatorService2.class);


    public CryptoApiNegotiatorService2() {
        super();
        logger.info("New CryptoNegotiatorService2");
    }

    @Override
    public List<PriceHistory> getPriceHistory() {
        List<PriceHistory> priceHistorys = null;
        return priceHistorys;

    }

    @Override
    public boolean isAvailable(){
        Random rand = new Random();
        int bool = rand.nextInt(2);
        return bool !=0;
    }
}