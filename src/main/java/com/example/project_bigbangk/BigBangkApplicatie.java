// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk;

import com.example.project_bigbangk.repository.RootRepository;
import com.example.project_bigbangk.service.ClientFactory;
import com.example.project_bigbangk.service.priceHistoryUpdate.*;
import org.h2.util.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class BigBangkApplicatie implements ApplicationListener<ContextRefreshedEvent> {

    private final int UPDATE_INTERVAL_PRICEUPDATESERVICE = 300000;//5min
    private final int NUMBER_OF_CLIENTS_TO_SEED = 3000;
    private static final String CURRENT_CURRENCY = "EUR";

    private final PriceHistoryUpdateService priceHistoryUpdateService;
    private ClientFactory clientFactory;
    private final Logger logger = LoggerFactory.getLogger(BigBangkApplicatie.class);
    private RootRepository rootRepository;

    public BigBangkApplicatie(PriceHistoryUpdateService priceHistoryUpdateService,
                              ICryptoApiNegotiatorStrategy cryptoApiNegotiatorStrategy,
                              ClientFactory clientFactory,
                              RootRepository rootrepository) {
        super();
        logger.info("New BigBangkApplicatie");
        this.priceHistoryUpdateService = priceHistoryUpdateService;
        this.clientFactory = clientFactory;
        this.rootRepository = rootrepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        startPriceHistoryUpdateTimer();
        try {
            SeedDatabse seedDatabse = new SeedDatabse();
         // seedDatabse.call();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void startPriceHistoryUpdateTimer() {
        Timer priceHistoryUpdateCallBack = new Timer(true);
        logger.info("priceHistoryUpdate in progress");
        priceHistoryUpdateCallBack.schedule(new UpdatePriceHisToryTask(), 3000, UPDATE_INTERVAL_PRICEUPDATESERVICE);
    }

    class UpdatePriceHisToryTask extends TimerTask {
        @Override
        public void run() {
            priceHistoryUpdateService.updatePriceHistory(CURRENT_CURRENCY);
        }
    }

    class SeedDatabse extends Task {
        @Override
        //ToDo maak deze switch goed.
        public void call() throws Exception {
            System.out.print("Start database seeding? (Y/N): ");
            Scanner scanner = new Scanner(System.in);
            while (!scanner.nextLine().equalsIgnoreCase("Y")) {
                logger.info("Press Y or N");
            }
            clientFactory.seedDataBase(NUMBER_OF_CLIENTS_TO_SEED);
        }
    }
}
