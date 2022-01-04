package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;

import java.util.List;

public interface ICryptoApiNegotiator {
    /**
     *
     * @return a list of currentPrices for the 20 most populair assets
     * as definied in AsseCode_Name
     * @param currency the currency for the prices of the assets
     */
    List<PriceHistory> getPriceHistory(String currency);

    /**
     * checks if the API server is online
     * @return true if the server is online
     */
    boolean isAvailable();
}
