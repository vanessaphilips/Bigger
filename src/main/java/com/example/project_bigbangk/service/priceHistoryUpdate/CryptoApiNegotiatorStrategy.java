// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptoApiNegotiatorStrategy implements ICryptoApiNegotiatorStrategy {

    private final Logger logger = LoggerFactory.getLogger(CryptoApiNegotiatorStrategy.class);
    private List<ICryptoApiNegotiatorService> cryptoNegotiatorServices;

    public CryptoApiNegotiatorStrategy(List<ICryptoApiNegotiatorService> cryptoNegotiatorServices) {
        super();
        logger.info("New CryptoNegotiatorStrategy");
        this.cryptoNegotiatorServices = cryptoNegotiatorServices;
        cryptoNegotiatorServices.add(new CoinMarketCapNegociator());
        //cryptoNegotiatorServices.add(new CryptoApiNegotiatorService2());
       // cryptoNegotiatorServices.add(new CryptoApiNegotiatorService3());
    }

    @Override
    public ICryptoApiNegotiatorService getAvailableCryptoService() {
                Optional<ICryptoApiNegotiatorService> cryptoApiNegotiatorService = cryptoNegotiatorServices.stream().filter(c->c.isAvailable()).findFirst();
                return cryptoApiNegotiatorService.isPresent()? cryptoApiNegotiatorService.get(): null;
    }
}