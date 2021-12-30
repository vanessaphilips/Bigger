package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;
import com.example.project_bigbangk.service.AddressService;
import com.example.project_bigbangk.service.ClientService;
import com.example.project_bigbangk.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Rootrepository test voor de PriceHistory sectie 
 * @author Pieter jan Bleichrodt
 */
@SpringBootTest
class RootRepositoryPriceHistoryAssetTest {
    @Resource

    private RootRepository rootRepository;
    @MockBean
    AddressService addressService;
    @MockBean
    ClientService clientService;
    @MockBean
    WalletService walletService;

    @MockBean
    private IPriceHistoryDAO jdbcPriceHistoryDAO;
    @MockBean
    private IAssetDAO jdbcAssetDAO;

    private List<Asset> assets = new ArrayList<>();
    private List<PriceHistory> priceHistories = new ArrayList<>();
    private LocalDateTime localDateTime ;

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
        localDateTime = LocalDateTime.now().minusSeconds(20);
        for (Asset asset : assets) {
            List<PriceHistory> priceHistoriesByAsset = priceHistories.stream().filter(ph -> ph.getAsset().getCode().equals(asset.getCode())).collect(Collectors.toList());
            Mockito.when(jdbcPriceHistoryDAO.getPriceHistoriesByCodeFromDate(localDateTime, asset.getCode())).thenReturn(priceHistoriesByAsset);
        }
    }

    @Test
    void getAllPriceHistroriesByAssets() {
        Mockito.when(jdbcAssetDAO.getAllAssets()).thenReturn(assets);
        List<List<PriceHistory>> priceHistories = rootRepository.getAllPriceHistroriesByAssets(localDateTime);
        assertEquals(12, priceHistories.size());
    }

    @Test
    void getAllPriceHistroriesByAssets2() {
        Mockito.when(jdbcAssetDAO.getAllAssets()).thenReturn(null);
        List<List<PriceHistory>> priceHistories = rootRepository.getAllPriceHistroriesByAssets(localDateTime);
        assertNull(priceHistories);
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

        assets.add(asset);
        return asset;
    }

}