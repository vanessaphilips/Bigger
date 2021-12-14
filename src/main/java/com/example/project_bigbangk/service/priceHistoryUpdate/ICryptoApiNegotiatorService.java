package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;

import java.util.List;

public interface ICryptoApiNegotiatorService {
    List<PriceHistory> getPriceHistory();

    boolean isAvailable();
}
