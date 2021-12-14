// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.model;

import com.example.project_bigbangk.service.priceHistoryUpdate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class BigBangkApplicatie implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(BigBangkApplicatie.class);
    private final int UPDATE_INTERVAL_PRICEUPDATESERVICE = 60000;
  //   private Bank bank;
    private final PriceHistoryUpdateService priceHistoryUpdateService;
   // List<ICryptoApiNegotiatorService> cryptoApiNegotiatorServiceList;

    public BigBangkApplicatie(PriceHistoryUpdateService priceHistoryUpdateService) {
        super();
        logger.info("New BigBangkApplicatie");
        this.priceHistoryUpdateService = priceHistoryUpdateService;
       // cryptoApiNegotiatorServiceList = new ArrayList<>();
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
       startTimer();
    }
    private void startTimer() {
        Timer timer = new Timer(true);
        logger.info("update start");
        timer.schedule(new Task1(), 1, UPDATE_INTERVAL_PRICEUPDATESERVICE);

    }

    class Task1 extends TimerTask {
        @Override
        public void run() {
            priceHistoryUpdateService.updatePriceHistory();
        }
    }
}
