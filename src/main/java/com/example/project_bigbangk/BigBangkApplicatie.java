// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk;

import com.example.project_bigbangk.service.priceHistoryUpdate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class BigBangkApplicatie implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(BigBangkApplicatie.class);
    private final int UPDATE_INTERVAL_PRICEUPDATESERVICE = 300000;//5min
    //ToDO Misschien nog een Singleton bank object hier instantieren? Zodat de bank er altijd is.
    private final PriceHistoryUpdateService priceHistoryUpdateService;
    private final ICryptoApiNegotiatorStrategy cryptoApiNegotiatorStrategy;


    public BigBangkApplicatie(PriceHistoryUpdateService priceHistoryUpdateService, ICryptoApiNegotiatorStrategy cryptoApiNegotiatorStrategy) {
        super();
        logger.info("New BigBangkApplicatie");
        this.priceHistoryUpdateService = priceHistoryUpdateService;
        this.cryptoApiNegotiatorStrategy = cryptoApiNegotiatorStrategy;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeUpdateStragegy();
        startPriceHistoryUpdateTimer();
    }

    private void initializeUpdateStragegy() {
        ICryptoApiNegotiatorService coinMarketCapNegotiator = new CoinMarketCapNegociator();
        cryptoApiNegotiatorStrategy.addNegotiator(coinMarketCapNegotiator);
    }

    private void startPriceHistoryUpdateTimer() {
        Timer priceHistoryUpdateCallBack = new Timer(true);
        logger.info("priceHistoryUpdate in progress");
        priceHistoryUpdateCallBack.schedule(new UpdatePriceHisToryTask(), 1, UPDATE_INTERVAL_PRICEUPDATESERVICE);

    }

    class UpdatePriceHisToryTask extends TimerTask {
        @Override
        public void run() {
            priceHistoryUpdateService.updatePriceHistory();
        }
    }
}
