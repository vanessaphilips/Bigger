package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcPriceHistoryDAOTest {

    @Resource
    JdbcPriceHistoryDAO priceHistoryDAO;

    private List<Asset> assets = new ArrayList<>();
    private List<PriceHistory> priceHistories = new ArrayList<>();


    @BeforeEach
    public void setup() {
        priceHistories.add(createPriceHisory(0.7, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHisory(1.3, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHisory(1.1, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHisory(1.21, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHisory(1.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHisory(4.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHisory(2.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHisory(6.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHisory(1.5, createAsset(AssetCode_Name.ADA)));
        priceHistories.add(createPriceHisory(5.5, createAsset(AssetCode_Name.ADA)));
        priceHistories.add(createPriceHisory(2.5, createAsset(AssetCode_Name.ADA)));
        priceHistories.add(createPriceHisory(0.5, createAsset(AssetCode_Name.ADA)));
    }


    @Test
    void savePriceHistory() {
        for (PriceHistory priceHistory : priceHistories) {
            priceHistoryDAO.savePriceHistory(priceHistory);
        }
        double actual = priceHistoryDAO.getCurrentPriceByAssetCode(AssetCode_Name.BTC.getAssetCode());
        double expected = 1.21;
        assertEquals(expected, actual);
        actual = priceHistoryDAO.getCurrentPriceByAssetCode(AssetCode_Name.ETH.getAssetCode());
        expected = 6.3;
        assertEquals(expected, actual);
    }


    private PriceHistory createPriceHisory(double currentprice, Asset asset) {
        PriceHistory priceHistory = Mockito.mock(PriceHistory.class);
        Mockito.when(priceHistory.getPrice()).thenReturn(currentprice);
        Mockito.when(priceHistory.getDateTime()).thenReturn(LocalDateTime.now());
        Mockito.when(priceHistory.getAsset()).thenReturn(asset);
        return priceHistory;
    }

    private Asset createAsset(AssetCode_Name assetCodeName) {
        Asset asset = Mockito.mock(Asset.class);
        Mockito.when(asset.getName()).thenReturn(assetCodeName.getAssetName());
        Mockito.when(asset.getCode()).thenReturn(assetCodeName.getAssetCode());
        Mockito.when(asset.getCurrentPrice()).thenReturn(1.0);

        return asset;
    }

}