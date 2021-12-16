// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk;

import com.example.project_bigbangk.repository.RootRepository;
import com.example.project_bigbangk.service.ClientFactory;
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

    private final int UPDATE_INTERVAL_PRICEUPDATESERVICE = 300000;//5min
    private final int NUMBER_OF_CLIENTS_TO_SEED = 3000;


    //ToDO Misschien nog een Singleton bank object hier instantieren? Zodat de bank er altijd is.
    private final PriceHistoryUpdateService priceHistoryUpdateService;
    private final ICryptoApiNegotiatorStrategy cryptoApiNegotiatorStrategy;
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
        this.cryptoApiNegotiatorStrategy = cryptoApiNegotiatorStrategy;
        this.clientFactory = clientFactory;
        this.rootRepository = rootrepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeUpdateStragegy();
        startPriceHistoryUpdateTimer();
        //  seedDataBase();
    }

    private void seedDataBase() {
        //TODO checken of er al wat in de DB staat
        clientFactory.createClients(NUMBER_OF_CLIENTS_TO_SEED);
    }

    private void initializeUpdateStragegy() {
        ICryptoApiNegotiatorService coinMarketCapNegotiator = new CoinMarketCapNegotiator();
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
