package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Model created by Vanessa Philips.
 * Object "Limit_Sell" > Sell transaction if the price reaches above the current level.
 */

public class Limit_Sell extends AbstractOrder{

    private final Logger logger = LoggerFactory.getLogger(Limit_Sell.class);

    private Wallet seller;

    public Limit_Sell(int orderId, double requestedPrice, double numberOfAssets, LocalDateTime date) {
        super(orderId, requestedPrice, numberOfAssets, date);
        logger.info("New Limit_Sell, without Asset and Wallet");
    }

    public Limit_Sell(Asset asset, double requestedPrice, double numberOfAssets, LocalDateTime date, Wallet sellerWallet) {
        super(asset, requestedPrice, numberOfAssets, date);
        this.sellerWallet = sellerWallet;
        logger.info("New Limit_Sell, without id");
    }

    public Wallet getSeller() {
        return seller;
    }

    public void setSeller(Wallet seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Limit_Sell{" +
                ", sellerWallet=" + seller +
                '}';
    }
}