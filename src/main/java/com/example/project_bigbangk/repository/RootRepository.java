// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.Wallet;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RootRepository {

   private IClientDAO clientDAO;
   private IAddressDAO addressDAO;
   private IWalletDAO walletDAO;

   public RootRepository(IClientDAO clientDAO, IAddressDAO addressDAO, IWalletDAO walletDAO) {
      this.clientDAO = clientDAO;
      this.addressDAO = addressDAO;
      this.walletDAO = walletDAO;
   }

   // CLIENT

   public Client findClientByEmail(String email) {
      Client client = clientDAO.findClientByEmail(email);
      if (client == null) {
      }
      return client;
   }

   /**
    * Saves address, wallet and client seperately in Database.
    * @param client
    */
   public void createNewlyRegisteredClient(Client client){
      addressDAO.save(client.getAddress());
      walletDAO.createNewWallet(client.getWallet());
      clientDAO.saveClient(client);
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
