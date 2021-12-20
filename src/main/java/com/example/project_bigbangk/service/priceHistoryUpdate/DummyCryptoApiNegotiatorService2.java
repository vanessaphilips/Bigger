// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Dummy class, remove in future
 */
//ToDo remove thhis class in futuire
public class DummyCryptoApiNegotiatorService2 implements ICryptoApiNegotiatorService {

    private final Logger logger = LoggerFactory.getLogger(DummyCryptoApiNegotiatorService2.class);


    public DummyCryptoApiNegotiatorService2() {
        super();
        logger.info("New CryptoNegotiatorService2");
    }

    @Override
    public List<PriceHistory> getPriceHistory(String currency) {
        List<PriceHistory> priceHistorys = null;
        return priceHistorys;

    }

    @Override
    public boolean isAvailable(){
      return false;
    }
}