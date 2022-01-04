package com.example.project_bigbangk.service.priceHistoryUpdate;
/**
 *
 * Interface for CryptoApiSwitcher which determines which Api Server is online and injects it in the
 * PriceHistoryUpdateService
 */
public interface ICryptoApiSwitcher {
    /**
     * selects which Api server is online
     * @return concrete implementation of ICryptoApiNegotiator
     */
    ICryptoApiNegotiator getAvailableNegotiator();
    /**
     * for adding additional concrete implementation of ICryptoApiNegotiator
     */
    void addNegotiator(ICryptoApiNegotiator coinMarketCapNegotiator);
}
