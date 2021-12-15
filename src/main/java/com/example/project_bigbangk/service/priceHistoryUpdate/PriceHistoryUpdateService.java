// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;
import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class PriceHistoryUpdateService {

    private final Logger logger = LoggerFactory.getLogger(PriceHistoryUpdateService.class);
    private ICryptoApiNegotiatorStrategy cryptoApiNegotiatorStrategy;
    private ICryptoApiNegotiatorService cryptoNegotiatorService;
    private RootRepository rootRepository;


    public PriceHistoryUpdateService(ICryptoApiNegotiatorStrategy cryptoApiNegotiatorStrategy, RootRepository rootRepository) {
        super();
        logger.info("New PriceHistoryUpdateService");
        this.cryptoApiNegotiatorStrategy = cryptoApiNegotiatorStrategy;
        this.rootRepository = rootRepository;
    }

    public void updatePriceHistory() {
        List<PriceHistory> priceHistories = null;
        cryptoNegotiatorService = cryptoApiNegotiatorStrategy.getAvailableCryptoService();
        priceHistories = cryptoNegotiatorService.getPriceHistory();
        if (priceHistories != null) {
            rootRepository.savePriceHistories(priceHistories);
            logger.info(String.format("Price history updated on %s", LocalDateTime.now()));
        } else {
            logger.error(String.format("Price history update encountered an error on %s", LocalDateTime.now()));
        }
    }
}
