package com.example.project_bigbangk.service;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

import com.example.project_bigbangk.model.DTO.OrderDTO;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;

@Service
public class Orderservice {

    private RootRepository rootRepository;


    public Orderservice(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public void executeOrderByType(OrderDTO order){
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

    public void executeBuyOrder(OrderDTO order){

        double currentPrice = 0;
        //double boughtAssetAmount = order.getAmount() / currentPrice;

        //email uit token of waar we ook loggedin user bewaren.
        String email= "philip.beeltje@gmail.com";

        //client client = rootrepo get client
        //Wallet bankWallet = rootRepository.getWalletByBankCode (met assets)

        //if(client.getWallet.getbalance >= order.getAmount && bankWallet.asset.getvalue(order.code) >= boughtAssetAmount){
        //
        //creër order object
        // UPDATE balans en asset van bank en klant
        //sla order op in database
        //// ^^ als dit niet lukt is de order alsnog uitgevoerd...misschien raar?
        //
        // }

        //^krijg je problemen als bank meerdere orders tegelijk doet?

        //return 1. of specifiek error/success bericht terug naar executeOrderbyType.
    }

    public void executeSellOrder(OrderDTO order){

    }


}
