package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
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
        priceHistories.add(createPriceHistory(0.7, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHistory(1.3, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHistory(1.1, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHistory(1.21, createAsset(AssetCode_Name.BTC)));
        priceHistories.add(createPriceHistory(1.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHistory(4.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHistory(2.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHistory(6.3, createAsset(AssetCode_Name.ETH)));
        priceHistories.add(createPriceHistory(1.5, createAsset(AssetCode_Name.ADA)));
        priceHistories.add(createPriceHistory(5.5, createAsset(AssetCode_Name.ADA)));
        priceHistories.add(createPriceHistory(2.5, createAsset(AssetCode_Name.ADA)));
        priceHistories.add(createPriceHistory(0.5, createAsset(AssetCode_Name.ADA)));
    }


    @Test
    void getCurrentPriceByAssetCode() {
        for (PriceHistory priceHistory : priceHistories) {
            priceHistoryDAO.savePriceHistory(priceHistory);
        }
        double actual = priceHistoryDAO.getCurrentPriceByAssetCode(AssetCode_Name.BTC.getAssetCode());
        double expected = 1.21;
        assertEquals(expected, actual);
        actual = priceHistoryDAO.getCurrentPriceByAssetCode(AssetCode_Name.ETH.getAssetCode());
        expected = 6.3;
        assertEquals(expected, actual);
        actual = priceHistoryDAO.getCurrentPriceByAssetCode(AssetCode_Name.BUSD.getAssetCode());
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getAllPriceHistory() {
        List<PriceHistory> actual = priceHistoryDAO.getPriceHistoriesByCodeFromDate(LocalDateTime.now().minusYears(20), "BTC");
        assertThat(actual.size()).isEqualTo(5);
        for (PriceHistory priceHistory : priceHistories) {
            priceHistoryDAO.savePriceHistory(priceHistory);
        }
        actual = priceHistoryDAO.getPriceHistoriesByCodeFromDate(LocalDateTime.now().minusSeconds(20), "BTC");
        assertThat(actual.size()).isEqualTo(4);
        actual = priceHistoryDAO.getPriceHistoriesByCodeFromDate(LocalDateTime.now().minusSeconds(20), "BIC");
        assertNull(actual);
    }
    @Test
    void getAllPriceHistoryFail() {
        List<PriceHistory>  actual = priceHistoryDAO.getPriceHistoriesByCodeFromDate(LocalDateTime.now().minusSeconds(20), "BIC");
        assertEquals(actual.size(),0);
    }

    private PriceHistory createPriceHistory(double currentprice, Asset asset) {
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