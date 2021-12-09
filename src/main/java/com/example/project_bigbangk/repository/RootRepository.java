// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.model.Wallet;
import org.springframework.stereotype.Repository;

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



   public Address findAddressByEmail(String email) {
      Address address = addressDAO.findAddressByEmail(email);
      if (address == null) {
      }
      return address;
   }

   /**
    * Saves address, wallet and client seperately in Database.
    * @param client
    */
   public void createNewlyRegisteredClient(Client client){
      addressDAO.saveAddress(client.getAddress());
      walletDAO.createNewWallet(client.getWallet());
      clientDAO.saveClient(client);
      //FIXME moet nog hier of in addressDAO opvangen wat er gebeurt als een address er al in staat, client check ik al in registratieservice
      //


   }


   // IBAN

   public Wallet findWalletByIban(String iban) {
      return walletDAO.findWalletByIban(iban);
   }
}
