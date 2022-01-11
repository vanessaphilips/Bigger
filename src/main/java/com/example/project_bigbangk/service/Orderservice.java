package com.example.project_bigbangk.service;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.DTO.OrderDTO;
import com.example.project_bigbangk.model.Orders.Transaction;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Orderservice {

    double currentAssetPrice;
    Asset asset;
    private RootRepository rootRepository;


    public Orderservice(RootRepository rootRepository, BigBangkApplicatie bigBangkApplicatie) {
        this.rootRepository = rootRepository;
    }

    public void executeOrderByType(OrderDTO order){
        currentAssetPrice = rootRepository.getCurrentPriceByAssetCode(order.getCode());
        asset = rootRepository.findAssetByCode(order.getCode());
        //if type = x y z bla bla, stuur naar andere methode.

        //types:
        // BuyOrder (alleen met bank)   code: Buy
        // SellOrder (alleen met bank)  code: Sell
        // Limit_Buy                    code: Lbuy
        // Limit_Sell                   code: Lsell
        // Stoploss_Sell                code: Sloss

        if(order.getType().equals("Buy")){
            executeBuyOrder(order);
        }
        if(order.getType().equals("Sell")){
            executeSellOrder(order);
        }

    }
    public String executeBuyOrder(OrderDTO order){
        double boughtAssetAmount = order.getAmount() / currentAssetPrice;
        double orderFee = order.getAmount() * BigBangkApplicatie.bigBangk.getFeePercentage();
        double totalCost = order.getAmount() + orderFee;

        String email= "Nandini@Wanadoo.org";//temp email
        //authenticationservice heeft methode voor client uit token
        Wallet clientWallet = rootRepository.findWalletByEmail(email);
        Wallet bankWallet = rootRepository.findWalletbyBankCode(BigBangkApplicatie.bigBangk.getCode());

        if(clientWallet.getBalance() >= totalCost){
            if(bankWallet.getAsset().get(asset) >= boughtAssetAmount){
                Transaction transaction = new Transaction(asset, order.getAmount(), boughtAssetAmount, LocalDateTime.now(), orderFee, clientWallet, bankWallet);
                // UPDATE balans en asset van klant
                clientWallet.setBalance(clientWallet.getBalance()-totalCost);
                clientWallet.getAsset().replace(asset, clientWallet.getAsset().get(asset) + boughtAssetAmount);
                rootRepository.updateWalletBalanceAndAsset(clientWallet, asset, clientWallet.getAsset().get(asset));
                // UPDATE balans en asset van bank
                bankWallet.setBalance(bankWallet.getBalance() + totalCost);
                bankWallet.getAsset().replace(asset, bankWallet.getAsset().get(asset) - boughtAssetAmount);
                rootRepository.updateWalletBalanceAndAsset(bankWallet, asset, bankWallet.getAsset().get(asset));
                //Sla transactie op
                //TODO all deze rootrepo aanroepen moeten uiteindelijk maar een methode in rootrepo worden, nu een beetje een rommeltje
                rootRepository.saveNewTransaction(transaction);
                return "Order successful. Bought " + boughtAssetAmount + " " + asset.getCode() + " for " + totalCost + "€.";
            } else{
                return  "Cannot complete order: Insufficient " + order.getCode();
            }
         } else {
            return "Cannot complete order: Insufficient funds.";
        }
    }

    public String executeSellOrder(OrderDTO order){
        double sellOrderValue = order.getAmount() * currentAssetPrice;
        double orderFee = sellOrderValue * BigBangkApplicatie.bigBangk.getFeePercentage();
        double totalPayout = sellOrderValue - orderFee;

        //email uit token
        String email= "Aad@Yahoo.fr";//temp email
        Wallet clientWallet = rootRepository.findWalletByEmail(email);
        Wallet bankWallet = rootRepository.findWalletbyBankCode(BigBangkApplicatie.bigBangk.getCode());

        if(bankWallet.getBalance() >= totalPayout) {
            if (clientWallet.getAsset().get(asset) >= order.getAmount()) {
                Transaction transaction = new Transaction(asset, sellOrderValue, order.getAmount(), LocalDateTime.now(), orderFee, bankWallet, clientWallet);
                // UPDATE balans en asset van klant
                clientWallet.setBalance(clientWallet.getBalance() + totalPayout);
                clientWallet.getAsset().replace(asset, clientWallet.getAsset().get(asset) - order.getAmount());
                rootRepository.updateWalletBalanceAndAsset(clientWallet, asset, clientWallet.getAsset().get(asset));
                // UPDATE balans en asset van bank
                bankWallet.setBalance(bankWallet.getBalance() - totalPayout);
                bankWallet.getAsset().replace(asset, bankWallet.getAsset().get(asset) + order.getAmount());
                rootRepository.updateWalletBalanceAndAsset(bankWallet, asset, bankWallet.getAsset().get(asset));
                //Sla transactie op
                //TODO all deze rootrepo aanroepen moeten uiteindelijk maar een methode in rootrepo worden, nu een beetje een rommeltje
                rootRepository.saveNewTransaction(transaction);
                return "Order successful. Sold " + order.getAmount() + " for "+ totalPayout + "€.";
            } else{
                return "Cannot complete order: Bank has insufficient funds.";
            }
        } else {
            return  "Cannot complete order: Insufficient " + order.getCode();
        }
    }

}
