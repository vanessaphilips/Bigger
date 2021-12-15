// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoApiNegotiatorStrategy implements ICryptoApiNegotiatorStrategy {

    private final Logger logger = LoggerFactory.getLogger(CryptoApiNegotiatorStrategy.class);
    private List<ICryptoApiNegotiatorService> cryptoApiNegotiatorServices;

    public CryptoApiNegotiatorStrategy() {
        super();
        logger.info("New CryptoNegotiatorStrategy");
        this.cryptoApiNegotiatorServices = new ArrayList<>();
    }

    @Override
    public ICryptoApiNegotiatorService getAvailableCryptoService() {
        Optional<ICryptoApiNegotiatorService> cryptoApiNegotiatorService = cryptoApiNegotiatorServices.stream().filter(c -> c.isAvailable()).findFirst();
        return cryptoApiNegotiatorService.orElse(null);
    }

    @Override
    public void addNegotiator(ICryptoApiNegotiatorService coinMarketCapNegotiator) {
        cryptoApiNegotiatorServices.add(coinMarketCapNegotiator);
    }
}