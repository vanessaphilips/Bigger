// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * This class is selects the CryptoApiNegotiatorService that is online and injects it in the
 * PriceHistoryUpdateService
 */
@Service
public class CryptoApiSwitcherService implements ICryptoApiSwitcherStrategy {

    private final Logger logger = LoggerFactory.getLogger(CryptoApiSwitcherService.class);
    private List<ICryptoApiNegotiatorService> cryptoApiNegotiatorServices;


    public CryptoApiSwitcherService() {
        super();
        logger.info("New CryptoApiSwitcherService");
        this.cryptoApiNegotiatorServices = new ArrayList<>();

    }

    /**
     * selects which Api server is online
     * @return concrete implementation of ICryptoApiNegotiatorService
     */
    @Override
    public ICryptoApiNegotiatorService getAvailableCryptoService() {
        Optional<ICryptoApiNegotiatorService> cryptoApiNegotiatorService = cryptoApiNegotiatorServices.stream().filter(c -> c.isAvailable()).findFirst();
        return cryptoApiNegotiatorService.orElse(null);
    }
    /**
     * for adding additional concrete implementation of ICryptoApiNegotiatorService
     */
    @Override
    public void addNegotiator(ICryptoApiNegotiatorService coinMarketCapNegotiator) {
        cryptoApiNegotiatorServices.add(coinMarketCapNegotiator);
    }
}