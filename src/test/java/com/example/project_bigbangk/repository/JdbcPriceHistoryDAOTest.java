package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcPriceHistoryDAOTest {

    @Resource
    JdbcPriceHistoryDAO priceHistoryDAO;

//    JdbcPriceHistoryDAOTest(JdbcPriceHistoryDAO jdbcPriceHistoryDAO){
//        this.priceHistoryDAO = jdbcPriceHistoryDAO;
//    }
    @Test
    void savePriceHistory() {
        //public PriceHistory(LocalDateTime dateTime, double price, Asset asset)
        //public Asset(AssetCode_Name assetCodeName, double currentPrice)

        PriceHistory priceHistory = new PriceHistory( LocalDateTime.now(), 1, new Asset(AssetCode_Name.ADA, 0.5));
        PriceHistory priceHistory2 = new PriceHistory( LocalDateTime.now(), 4, new Asset(AssetCode_Name.ETH, 0.5));
        PriceHistory priceHistory3 = new PriceHistory( LocalDateTime.now(), 6, new Asset(AssetCode_Name.BNB, 0.5));
        PriceHistory priceHistory4 = new PriceHistory( LocalDateTime.now(), 9, new Asset(AssetCode_Name.BTC, 0.5));
        priceHistoryDAO.savePriceHistory(priceHistory);
        priceHistoryDAO.savePriceHistory(priceHistory2);
        priceHistoryDAO.savePriceHistory(priceHistory3);
        priceHistoryDAO.savePriceHistory(priceHistory4);
        double actual = priceHistoryDAO.getCurrentPriceByAssetCodeName(AssetCode_Name.BTC);
        double expected = 9;
        assertEquals(expected, actual);

    }

    @Test
    void getCurrentPriceByAssetCode() {
    }
}