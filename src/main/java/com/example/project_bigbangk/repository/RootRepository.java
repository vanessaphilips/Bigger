// Created by RayS
// Creation date 3-12-2021

/* Ray: RootRepository alvast gemaakt, bleek een methode in mijn voorbeelden te zitten waarin die werd aangeroepen.
 * Dus voor Address-Service maar gelijk meegenomen
 */

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

   private JdbcClientDAO jdbcClientDAO;
   private JdbcAddressDAO jdbcAddressDAO;
   private JdbcWalletDAO jdbcWalletDAO;

   public RootRepository(JdbcClientDAO jdbcClientDAO, JdbcAddressDAO jdbcAddressDAO, JdbcWalletDAO jdbcWalletDAO) {
      this.jdbcClientDAO = jdbcClientDAO;
      this.jdbcAddressDAO = jdbcAddressDAO;
      this.jdbcWalletDAO = jdbcWalletDAO;
   }

   // CLIENT

   public Client findClientByEmail(String email) {
      Client client = jdbcClientDAO.findClientByEmail(email);
      if (client == null) {
      }
      return null;
   }
}
