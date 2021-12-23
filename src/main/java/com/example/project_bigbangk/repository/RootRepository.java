// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.*;
import com.example.project_bigbangk.model.Orders.Transaction;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class RootRepository {

    private IClientDAO clientDAO;
    private IAddressDAO addressDAO;
    private IWalletDAO walletDAO;
    private IAssetDAO assetDAO;
    private JdbcOrderDAO orderDAO;
    private final IPriceHistoryDAO priceHistoryDAO;
    private final int AMOUNT_OF_ASSETS = 20;

    public RootRepository(IClientDAO clientDAO, IAddressDAO addressDAO, IWalletDAO walletDAO,
                          IPriceHistoryDAO priceHistoryDAO, IAssetDAO assetDAO, JdbcOrderDAO orderDAO) {
        this.clientDAO = clientDAO;
        this.addressDAO = addressDAO;
        this.walletDAO = walletDAO;
        this.priceHistoryDAO = priceHistoryDAO;
        this.assetDAO = assetDAO;
        this.orderDAO = orderDAO;
    }

    // CLIENT

    public Client findClientByEmail(String email) {
        Client client = clientDAO.findClientByEmail(email);
        if (client != null) {
            Address adress = findAddressByEmail(email);
            client.setAddress(adress);
        }
        return client;
    }

    public Address findAddressByEmail(String email) {
        Address address = addressDAO.findAddressByEmail(email);
        return address;
    }

    /**
     * Saves address, wallet and client seperately in Database.
     *
     * @param client
     */
    public void createNewlyRegisteredClient(Client client) {
        addressDAO.saveAddress(client.getAddress());
        walletDAO.saveNewWallet(client.getWallet());
        clientDAO.saveClient(client);
    }

    //PriceHistory
    public void savePriceHistories(List<PriceHistory> priceHistories) {
        boolean saveAssets = assetDAO.getNumberOfAssets() != AMOUNT_OF_ASSETS;
        for (PriceHistory priceHistory : priceHistories) {
            if (saveAssets) {
                assetDAO.saveAsset(priceHistory.getAsset());
            }
            priceHistoryDAO.savePriceHistory(priceHistory);
        }
    }

    public List<List<PriceHistory>> getAllPriceHistroriesByAssets(LocalDateTime dateTime) {
        List<Asset> assets = assetDAO.getAllAssets();
        List<List<PriceHistory>> priceHistoriesAllAssets = new ArrayList<>();
        if (assets != null) {
            for (Asset asset : assets) {
                List<PriceHistory> priceHistories = priceHistoryDAO.getPriceHistoriesByCodeFromDate(dateTime, asset.getCode());
                asset.setCurrentPrice(Collections.max(priceHistories).getPrice());
                for (PriceHistory priceHistory : priceHistories) {
                    priceHistory.setAsset(asset);
                }
                priceHistoriesAllAssets.add(priceHistories);
            }
        }
        if (priceHistoriesAllAssets.size() != 0) {
            return priceHistoriesAllAssets;
        }
        return null;
    }

    //Asset
    public List<Asset> getAllAssets() {
        List<Asset> assets = assetDAO.getAllAssets();
        if (assets != null) {
            for (Asset asset : assets) {
                asset.setCurrentPrice(priceHistoryDAO.getCurrentPriceByAssetCode(asset.getCode()));
            }
        }
        return assets;
    }


    // WALLET

    //ToDO findWalletByEmail

    public void saveNewWallet(Wallet wallet) {
        walletDAO.saveNewWallet(wallet);
    }

    public Wallet findWalletByIban(String iban) {
        return walletDAO.findWalletByIban(iban);
    }

    public void updateWalletBalanceAndAsset(Wallet wallet, Asset asset, double amount) {
        walletDAO.updateBalance(wallet);
        walletDAO.updateWalletAssets(wallet, asset, amount);
    }

    public Wallet findWalletWithAssetByIban(String iban) {
        Wallet wallet = walletDAO.findWalletByIban(iban);
        if (wallet == null) {
            return wallet;
        }
        Map<Asset, Double> assetWithAmountMap = new HashMap<>();
        List<Asset> assets = assetDAO.getAllAssets();
        for (Asset asset : assets) {
            assetWithAmountMap.put(assetDAO.findAssetByCode(asset.getCode()), walletDAO.findAmountOfAsset(iban, asset.getCode()));
        }
        wallet.setAsset(assetWithAmountMap);
        return wallet;
    }

    //TODO onderstaande methode maken!

    private void updateWallet(Wallet sellerWallet) {
    }


    //ORDER > TRANSACTION

    /**
     * Saves Transaction, including sellerWallet and buyerWallet in database.
     * @param transaction
     */
    public void saveNewTransaction(Transaction transaction) {
        orderDAO.saveTransaction(transaction);
        updateWallet(transaction.getSellerWallet());
        updateWallet(transaction.getBuyerWallet());
    }
}
