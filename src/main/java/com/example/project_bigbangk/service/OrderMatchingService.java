// Created by Deek
// Creation date 1/18/2022

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.model.Orders.*;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderMatchingService {

    RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderMatchingService.class);

    public OrderMatchingService(RootRepository rootRepository) {
        super();
        logger.info("New OrderMatchingService");
        this.rootRepository = rootRepository;
    }


    //public List<AbstractOrder> checkForTriggered

    //limit buy ik wil kopen voor een bedrag niet hoger dan limit
    //limit sell ik wil kopen voor een bedrag niet lager dan limit
    public void checkForMatchingOrders() {
        List<Limit_Sell> allLimit_SellOrders = rootRepository.getAllOrdersbyType(TransactionType.LIMIT_SELL).stream()
                .map(t -> (Limit_Sell) t)
                .collect(Collectors.toList());
        List<Limit_Buy> allLimit_BuyOrders = rootRepository.getAllOrdersbyType(TransactionType.LIMIT_BUY).stream()
                .map(t -> (Limit_Buy) t)
                .collect(Collectors.toList());
        for (Limit_Buy limit_buy : allLimit_BuyOrders) {
            double requestedPricePerAsset = limit_buy.getRequestedPrice() / limit_buy.getNumberOfAssets();
            List<Limit_Sell> matches = allLimit_SellOrders.stream()
                    .filter(lso -> requestedPricePerAsset > lso.getRequestedPrice() / lso.getNumberOfAssets())
                    .collect(Collectors.toList());
            matches.sort(Comparator.comparing(AbstractOrder::getRequestedPrice).reversed().thenComparing(AbstractOrder::getDate).reversed());
            processMatches(limit_buy, matches);
        }
    }

    private void processMatches(Limit_Buy limit_buy, List<Limit_Sell> matches) {
        double amountOfAssets = limit_buy.getNumberOfAssets();
        int indexLimitSell = 0;
        while (amountOfAssets > 0 && indexLimitSell < matches.size()) {

            Limit_Sell limit_sellMatch = matches.get(indexLimitSell);
            double amountOfAssetsLimitSell = limit_sellMatch.getNumberOfAssets();
            double transactionAmountOfAssets = amountOfAssets - amountOfAssetsLimitSell < 0 ? amountOfAssets : amountOfAssetsLimitSell;
            double transActionfee = transactionAmountOfAssets * limit_sellMatch.getRequestedPrice() / limit_sellMatch.getNumberOfAssets();
           processTransaction(new Transaction(limit_buy.getAsset(),
                    limit_sellMatch.getRequestedPrice(),
                    transactionAmountOfAssets,
                    LocalDateTime.now(), transActionfee, limit_buy.getBuyerWallet(), limit_sellMatch.getSellerWallet()
            ));
        }
    }

    private void processTransaction(Transaction transaction) {
        Wallet buyerWallet = transaction.getBuyerWallet();
        Wallet sellerWallet = transaction.getSellerWallet();
        Wallet bankWallet = BigBangkApplicatie.bigBangk.getWallet();
        buyerWallet.setBalance(transaction.getBuyerWallet().getBalance() - transaction.getRequestedPrice());
        sellerWallet.setBalance(transaction.getSellerWallet().getBalance() + transaction.getRequestedPrice());
        buyerWallet.getAsset().replace(transaction.getAsset(), buyerWallet.getAsset().get(transaction.getAsset()) + transaction.getNumberOfAssets());
        sellerWallet.getAsset().replace(transaction.getAsset(), sellerWallet.getAsset().get(transaction.getAsset()) - transaction.getNumberOfAssets());
        bankWallet.setBalance(BigBangkApplicatie.bigBangk.getWallet().getBalance() + transaction.getTransactionFee());
        if (transaction.getBuyerWallet().equals(bankWallet)) {
            transaction.getSellerWallet().setBalance(transaction.getSellerWallet().getBalance() - transaction.getTransactionFee());
        } else if (transaction.getSellerWallet().equals(bankWallet)) {
            transaction.getBuyerWallet().setBalance(transaction.getBuyerWallet().getBalance() - transaction.getTransactionFee());
        } else {
            transaction.getSellerWallet().setBalance(transaction.getSellerWallet().getBalance() - transaction.getTransactionFee() / 2.0);
            transaction.getBuyerWallet().setBalance(transaction.getBuyerWallet().getBalance() - transaction.getTransactionFee() / 2.0);
        }
        rootRepository.saveTransaction(transaction);
    }



}