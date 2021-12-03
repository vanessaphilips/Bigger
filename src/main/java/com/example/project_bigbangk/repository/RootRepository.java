// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

   private AddressDAO addressDAO;

   public RootRepository(AddressDAO addressDAO) {
      this.addressDAO  = addressDAO;
   }

   public Address findAddressByEmail(String email) { return null;  }
}
