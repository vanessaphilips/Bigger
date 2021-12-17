package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;

public interface IPriceHistoryDAO {
    public void savePriceHistory(PriceHistory priceHistory);


    double getCurrentPriceByAssetCodeName(AssetCode_Name assetCodeName);
}
