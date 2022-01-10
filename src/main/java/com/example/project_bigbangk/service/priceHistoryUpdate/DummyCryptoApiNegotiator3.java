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
//ToDo remove thhis class in future
public class DummyCryptoApiNegotiator3 implements ICryptoApiNegotiator {

    private final Logger logger = LoggerFactory.getLogger(DummyCryptoApiNegotiator3.class);

    public DummyCryptoApiNegotiator3() {
        super();
        logger.info("New CryptoNegotiatorService3");
    }

    @Override
    public List<PriceHistory> getPriceHistory(String currency) {
        List<PriceHistory> priceHistorys = null;
        return priceHistorys;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}