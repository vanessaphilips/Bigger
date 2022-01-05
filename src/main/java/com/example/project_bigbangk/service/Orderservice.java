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
    private RootRepository rootRepository;
    private BigBangkApplicatie bigBangkApplicatie;


    public Orderservice(RootRepository rootRepository, BigBangkApplicatie bigBangkApplicatie) {
        this.rootRepository = rootRepository;
        this.bigBangkApplicatie = bigBangkApplicatie;
    }

    public void executeOrderByType(OrderDTO order){
        currentAssetPrice = rootRepository.getCurrentPriceByAssetCode(order.getCode());
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

        //email uit token
        String email= "Aad@Yahoo.fr";//temp email
        Wallet clientWallet = rootRepository.findWalletByEmail(email);
        Wallet bankWallet = rootRepository.findWalletbyBankCode(bigBangkApplicatie.bigBangk.getCode());
        Asset asset = rootRepository.findAssetByCode(order.getCode());

        if(clientWallet.getBalance() >= (order.getAmount()+orderFee)){
            if(bankWallet.getAsset().get(asset) >= boughtAssetAmount){
                Transaction transaction = new Transaction(asset, currentAssetPrice, boughtAssetAmount, LocalDateTime.now(), orderFee, clientWallet, bankWallet);
                // UPDATE balans en asset van klant
                clientWallet.setBalance(clientWallet.getBalance()-order.getAmount());
                clientWallet.getAsset().replace(asset, clientWallet.getAsset().get(asset) + boughtAssetAmount);
                rootRepository.updateWalletBalanceAndAsset(clientWallet, asset, clientWallet.getAsset().get(asset));
                // UPDATE balans en asset van bank
                bankWallet.setBalance(bankWallet.getBalance()+order.getAmount());
                bankWallet.getAsset().replace(asset, bankWallet.getAsset().get(asset) - boughtAssetAmount);
                rootRepository.updateWalletBalanceAndAsset(bankWallet, asset, bankWallet.getAsset().get(asset));
                //Sla transactie op
                //TODO all deze rootrepo aanroepen moeten uiteindelijk maar een methode in rootrepo worden, nu een beetje een rommeltje
                rootRepository.saveNewTransaction(transaction);
                return "Order successful.";
            } else{
                return  "Cannot complete order: Insufficient " + order.getCode();
            }
         } else {
            return "Cannot complete order: insufficient funds.";
        }
    }

    public void executeSellOrder(OrderDTO order){

    }


}
