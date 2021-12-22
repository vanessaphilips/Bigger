package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface IPriceHistoryDAO {
    public void savePriceHistory(PriceHistory priceHistory);

    double getCurrentPriceByAssetCode(String assetCode);

    List<PriceHistory> getPriceHistoriesByCodeFromDate(LocalDateTime date, String assetCode);
}
