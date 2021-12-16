package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class JdbcPriceHistoryDAOTest {
    JdbcPriceHistoryDAO priceHistoryDAO;

    JdbcPriceHistoryDAOTest(JdbcPriceHistoryDAO jdbcPriceHistoryDAO){
        this.priceHistoryDAO = jdbcPriceHistoryDAO;
    }
    @Test
    void savePriceHistory() {
        //public PriceHistory(LocalDateTime dateTime, double price, Asset asset)
        //public Asset(AssetCode_Name assetCodeName, double currentPrice)
        PriceHistory priceHistory = new PriceHistory( LocalDateTime.now(), 1, new Asset(AssetCode_Name.ADA, 1));
        priceHistoryDAO.savePriceHistory(priceHistory);
        priceHistoryDAO.getCurrentPriceByAssetCode(AssetCode_Name.ADA);

    }

    @Test
    void getCurrentPriceByAssetCode() {
    }
}