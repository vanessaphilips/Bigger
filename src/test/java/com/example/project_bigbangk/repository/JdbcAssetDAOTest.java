package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class JdbcAssetDAOTest {

    @Resource
    JdbcAssetDAO assetDAO;

    Asset BTC = new Asset("BTC", "Bitcoin");
    Asset PC = new Asset("PC", "PhilipCoin");
    Asset CC = new Asset("CC", "CoolCoin");

    @Test
    @Order(1)
    void getAllAssets() {
        assetDAO.saveAsset(BTC);
        assetDAO.saveAsset(PC);
        assetDAO.saveAsset(CC);
       List<Asset> testList = assetDAO.getAllAssets();
        System.out.println(testList.get(1).getCode());
        System.out.println(testList.get(1).getName());
       assertEquals(3, testList.size());
    }

    @Test
    @Order(2)
    void getNumberOfAssets() {
        int count = assetDAO.getNumberOfAssets();
        assertEquals(3, count);
    }

    @Test
    @Order(3)
    void findAssetByCode() {
        Asset BB = new Asset("BB", "Beebee");
        assetDAO.saveAsset(BB);
        Asset testBTC = assetDAO.findAssetByCode("BB");
        String code = "BB";
        assertEquals(code, testBTC.getCode());
    }

}