package com.example.project_bigbangk.service.priceHistoryUpdate;

public interface ICryptoApiNegotiatorStrategy {
    ICryptoApiNegotiatorService getAvailableCryptoService();

    void addNegotiator(ICryptoApiNegotiatorService coinMarketCapNegotiator);
}
