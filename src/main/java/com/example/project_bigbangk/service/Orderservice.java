package com.example.project_bigbangk.service;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.DTO.OrderDTO;
import com.example.project_bigbangk.model.Orders.Transaction;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class Orderservice {

    double currentAssetPrice;
    private Asset asset;
    private RootRepository rootRepository;
    private Client client;

    public Orderservice(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public enum Messages
    {
        FundClient("Order Failed: Client has insufficient funds."),
        FundBank("Order Failed: Bank has insufficient funds."),
        AssetClient("Order Failed: Client has insufficient assets."),
        AssetBank("Order Failed: Bank has insufficient assets."),
        SuccessBuy("Buy-Order successful"),
        SuccessSell("Sell-Order successful");
        private String body;

        Messages(String envBody) {
            this.body = envBody;
        }
        public String getBody() {
            return body;
        }
    }

    public String handleOrderByType(OrderDTO order, Client clientFromToken){
        client = clientFromToken;
        currentAssetPrice = rootRepository.getCurrentPriceByAssetCode(order.getCode());
        asset = rootRepository.findAssetByCode(order.getCode());

        //types:
        // BuyOrder (alleen met bank)   code: Buy       -klaar
        // SellOrder (alleen met bank)  code: Sell      -klaar
        // Limit_Buy                    code: Lbuy      -sprint 3
        // Limit_Sell                   code: Lsell     -sprint 3
        // Stoploss_Sell                code: Sloss     -sprint 3

        if(order.getType().equals("Buy")){
            return checkBuyOrder(order);
        }
        if(order.getType().equals("Sell")){
            return checkSellOrder(order);
        }else{
            return "Incorrect order type in JSON";
        }
    }

    public String checkBuyOrder(OrderDTO order){
        double boughtAssetAmount = order.getAmount() / currentAssetPrice;
        double orderFee = order.getAmount() * BigBangkApplicatie.bigBangk.getFeePercentage();
        double totalCost = order.getAmount() + orderFee;
        Wallet clientWallet = client.getWallet();
        Wallet bankWallet = rootRepository.findWalletbyBankCode(BigBangkApplicatie.bigBangk.getCode());

        if(clientWallet.getBalance() >= totalCost){
            if(bankWallet.getAsset().get(asset) >= boughtAssetAmount){
                executeBuyOrder(order, boughtAssetAmount, orderFee, totalCost, clientWallet, bankWallet);
                return Messages.SuccessBuy.getBody();
                
            } else{
                return Messages.AssetBank.getBody();
            }
         } else {
            return Messages.FundClient.getBody();
        }
    }

    private void executeBuyOrder(OrderDTO order, double boughtAssetAmount, double orderFee, double totalCost, Wallet clientWallet, Wallet bankWallet) {
        clientWallet.setBalance(clientWallet.getBalance()- totalCost);
        clientWallet.getAsset().replace(asset, clientWallet.getAsset().get(asset) + boughtAssetAmount);
        
        bankWallet.setBalance(bankWallet.getBalance() + totalCost);
        bankWallet.getAsset().replace(asset, bankWallet.getAsset().get(asset) - boughtAssetAmount);
        
        Transaction transaction = new Transaction(asset, order.getAmount(), boughtAssetAmount, LocalDateTime.now(), orderFee, clientWallet, bankWallet);
        sendOrderToDatabase(clientWallet, bankWallet, transaction);
    }

    public String checkSellOrder(OrderDTO order){
        double sellOrderValue = order.getAmount() * currentAssetPrice;
        double orderFee = sellOrderValue * BigBangkApplicatie.bigBangk.getFeePercentage();
        double totalPayout = sellOrderValue - orderFee;
        Wallet clientWallet = client.getWallet();
        Wallet bankWallet = rootRepository.findWalletbyBankCode(BigBangkApplicatie.bigBangk.getCode());

        if(bankWallet.getBalance() >= totalPayout) {
            if (clientWallet.getAsset().get(asset) >= order.getAmount()) {
                executeSellOrder(order, sellOrderValue, orderFee, totalPayout, clientWallet, bankWallet);
                return Messages.SuccessSell.getBody();
            } else{
                return Messages.FundBank.getBody();
            }
        } else {
            return  Messages.AssetClient.getBody();
        }
    }

    private void executeSellOrder(OrderDTO order, double sellOrderValue, double orderFee, double totalPayout, Wallet clientWallet, Wallet bankWallet) {
        clientWallet.setBalance(clientWallet.getBalance() + totalPayout);
        clientWallet.getAsset().replace(asset, clientWallet.getAsset().get(asset) - order.getAmount());

        bankWallet.setBalance(bankWallet.getBalance() - totalPayout);
        bankWallet.getAsset().replace(asset, bankWallet.getAsset().get(asset) + order.getAmount());

        Transaction transaction = new Transaction(asset, sellOrderValue, order.getAmount(), LocalDateTime.now(), orderFee, bankWallet, clientWallet);
        sendOrderToDatabase(clientWallet, bankWallet, transaction);
    }

    public void sendOrderToDatabase(Wallet walletOne, Wallet walletTwo, Transaction transaction){
        rootRepository.updateWalletBalanceAndAsset(walletOne, asset, walletOne.getAsset().get(asset));
        rootRepository.updateWalletBalanceAndAsset(walletTwo, asset, walletTwo.getAsset().get(asset));
        rootRepository.saveNewTransaction(transaction);
    }



}
