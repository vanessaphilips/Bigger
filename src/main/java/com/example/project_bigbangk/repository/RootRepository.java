// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.Wallet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RootRepository {

   private ClientDAO clientDAO;
   private AddressDAO addressDAO;
   private WalletDAO walletDAO;

   public RootRepository(ClientDAO clientDAO, AddressDAO addressDAO, WalletDAO walletDAO) {
      this.clientDAO = clientDAO;
      this.addressDAO = addressDAO;
      this.walletDAO = walletDAO;
   }

   // CLIENT

   public Client findClientByEmail(String email) {
      Client client = clientDAO.findClientByEmail(email);
      if (client == null) {
      }
      return null;
   }
}
