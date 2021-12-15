package com.example.project_bigbangk.service.priceHistoryUpdate;
/**
 *
 * Interface for CryptoApiNegotiatorStrategy which determines which Api Server is online and injects it in the
 * PriceHistoryUpdateService
 */
public interface ICryptoApiNegotiatorStrategy {
    /**
     * selects which Api server is online
     * @return concrete implementation of ICryptoApiNegotiatorService
     */
    ICryptoApiNegotiatorService getAvailableCryptoService();
    /**
     * for adding additional concrete implementation of ICryptoApiNegotiatorService
     */
    void addNegotiator(ICryptoApiNegotiatorService coinMarketCapNegotiator);
}
