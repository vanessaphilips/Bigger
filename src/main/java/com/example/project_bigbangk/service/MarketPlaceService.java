// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketPlaceService {

    private final Logger logger = LoggerFactory.getLogger(MarketPlaceService.class);
    private final RootRepository rootRepository;

    public MarketPlaceService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New MarketPlaceService");
    }

    public List<Asset> getAllAssets( ){
        return rootRepository.getAllAssets();
    }
}