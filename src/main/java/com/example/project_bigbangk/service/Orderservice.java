package com.example.project_bigbangk.service;
/*

@Author Philip Beeltje, Studentnummer: 500519452
*/

import com.example.project_bigbangk.model.DTO.OrderDTO;
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

        if(order.getType() == "Buy"){
            executeBuyOrder(order);
        }
        if(order.getType() == "Sell"){
            executeSellOrder(order);
        }

    }

    public void executeBuyOrder(OrderDTO order){
        //bereken hoeveel asset de gebruiker moet krijgen voor zijn geld met laatste koers.(ook al op front-end maar voor veiligheid hier ook)

        //check of mogelijk: gebruiker heeft voldoende balans, bank heeft voldoende asset, optioneel: coinprijs is up to date (volgens onze standaard)
        //^krijg je problemen als bank meerdere orders tegelijk doet?

        //creër order object

        // UPDATE balans en asset van bank en klant

        //sla order op in database
        // ^^ als dit niet lukt is de order alsnog uitgevoerd...misschien raar?

        //return 1. of specifiek error/success bericht terug naar executeOrderbyType.
    }

    public void executeSellOrder(OrderDTO order){

    }


}
