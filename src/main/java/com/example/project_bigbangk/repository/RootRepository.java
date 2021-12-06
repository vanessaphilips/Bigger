// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.model.Client;
import org.springframework.stereotype.Repository;


@Repository
public class RootRepository {

   private ClientDAO clientDAO;
   private AddressDAO addressDAO;

   public RootRepository(AddressDAO addressDAO, ClientDAO clientDAO) {
      this.addressDAO  = addressDAO;
      this.clientDAO  = clientDAO;
   }

   public Client findClientByEmail(String email){
      Client client = clientDAO.findClientByEmail(email);
      return client;
   }

   public Address findAddressByEmail(String email){
      return null;
   }
}
