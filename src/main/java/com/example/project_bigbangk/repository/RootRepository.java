// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RootRepository {

    private IClientDAO clientDAO;
    private IAddressDAO addressDAO;
    private IWalletDAO walletDAO;
    private IAssetDAO assetDAO;
    private final IPriceHistoryDAO priceHistoryDAO;

    public RootRepository(IClientDAO clientDAO, IAddressDAO addressDAO, IWalletDAO walletDAO, IPriceHistoryDAO priceHistoryDAO, IAssetDAO assetDAO) {
        this.clientDAO = clientDAO;
        this.addressDAO = addressDAO;
        this.walletDAO = walletDAO;
        this.priceHistoryDAO = priceHistoryDAO;
        this.assetDAO = assetDAO;
    }

    // CLIENT

    public Client findClientByEmail(String email) {
        Client client = clientDAO.findClientByEmail(email);
        if (client != null) {
            Address adress = findAddressByEmail(email);
            client.setAddress(adress);
            //ToDO findWalletByEmail
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
        walletDAO.createNewWallet(client.getWallet());
        clientDAO.saveClient(client);
    }

    //PriceHistory
    public void savePriceHistories(List<PriceHistory> priceHistories) {
        for (PriceHistory priceHistory : priceHistories) {
            assetDAO.saveAsset(priceHistory.getAsset());
            priceHistoryDAO.savePriceHistory(priceHistory);
        }
    }

    // IBAN

    public Wallet findWalletByIban(String iban) {
        return walletDAO.findWalletByIban(iban);
    }

    // WALLET

    //TODO methode invullen
    public void createNewWalletWithAssets() {
    }

    public void updateWalletBalanceAndAsset(Wallet wallet, Asset asset) {
        walletDAO.updateWalletBalanceAndAsset(wallet, asset);
    }

    //TODO methode invullen
//   public Wallet findWalletWithAssetByIban(String iban) {
//      Wallet wallet = walletDAO.findWalletByIban(iban);
//      if (wallet == null) {
//         return wallet;
//      }
//      Map<String, Double> assetMap = walletDAO.findAssetCodeWithAmount(iban);
//      Map<Asset, Double> returnAssetMap = null;
//      for ( String asset : assetMap.keySet()) {
    //TODO AssetDOA functies aanmaken
//         returnAssetMap.put(assetDOA.findAssetByCode(asset),assetMap.get(asset));
//      }
//      wallet.setAsset(returnAssetMap);
//
//      return wallet;
//   }
}
