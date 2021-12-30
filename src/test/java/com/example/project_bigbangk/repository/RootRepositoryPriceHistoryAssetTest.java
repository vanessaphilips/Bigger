package com.example.project_bigbangk.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class RootRepositoryPriceHistoryAssetTest {

    private RootRepository rootRepository;

    public RootRepositoryPriceHistoryAssetTest(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    //PriceHistory
//    public void savePriceHistories(List<PriceHistory> priceHistories) {
//        boolean saveAssets = assetDAO.getNumberOfAssets() != AMOUNT_OF_ASSETS;
//        for (PriceHistory priceHistory : priceHistories) {
//            if (saveAssets) {
//                assetDAO.saveAsset(priceHistory.getAsset());
//            }
//            priceHistoryDAO.savePriceHistory(priceHistory);
//        }
//    }
    @Test
    void savePriceHistories() {

    }

    //    public List<List<PriceHistory>> getAllPriceHistroriesByAssets(LocalDateTime dateTime) {
//        List<Asset> assets = assetDAO.getAllAssets();
//        List<List<PriceHistory>> priceHistoriesAllAssets = new ArrayList<>();
//        if (assets != null) {
//            for (Asset asset : assets) {
//                List<PriceHistory> priceHistories = priceHistoryDAO.getPriceHistoriesByCodeFromDate(dateTime, asset.getCode());
//                asset.setCurrentPrice(Collections.max(priceHistories).getPrice());
//                for (PriceHistory priceHistory : priceHistories) {
//                    priceHistory.setAsset(asset);
//                }
//                priceHistoriesAllAssets.add(priceHistories);
//            }
//        }
//        if (priceHistoriesAllAssets.size() != 0) {
//            return priceHistoriesAllAssets;
//        }
//        return null;
//    }
//
    @Test
    void getAllPriceHistroriesByAssets() {
        Mockito.mock()
        rootRepository.getAllPriceHistroriesByAssets()
    }
//    //Asset
//    public List<Asset> getAllAssets() {
//        List<Asset> assets = assetDAO.getAllAssets();
//        if (assets != null) {
//            for (Asset asset : assets) {
//                asset.setCurrentPrice(priceHistoryDAO.getCurrentPriceByAssetCode(asset.getCode()));
//            }
//        }
//        return assets;
//    }


}