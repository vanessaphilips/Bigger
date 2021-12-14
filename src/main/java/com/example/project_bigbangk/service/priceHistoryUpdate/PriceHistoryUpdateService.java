// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.PriceHistory;
import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        System.out.println("priceupdate gestart");
        List<PriceHistory> priceHistories = null;
            while (priceHistories == null) {
                cryptoNegotiatorService = cryptoApiNegotiatorStrategy.getAvailableCryptoService();
                priceHistories = cryptoNegotiatorService.getPriceHistory();
                rootRepository.savePriceHistories(priceHistories);
            }
    }
}
