// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.repository.JdbcClientDAO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private JdbcClientDAO jdbcClientDAO;
    private RootRepository rootRepository;

    public ClientService(JdbcClientDAO jdbcClientDAO, RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        this.jdbcClientDAO = jdbcClientDAO;
    }

    // TODO de volgende methode actief maken wanneer de RootRepository gereed is.
    //FIXME uiteindelijk moeten alle aanroepen van DAOs vanuit de root repository en niet in services,
    // komt dus nog een stapje tussen die nu vrij overbodig is maar later handig is (denk ik)

    public Client getClientByEmail(String email) {
        return rootRepository.findClientByEmail(email);
    }

    public List<Client> getAllClients() {
        return jdbcClientDAO.findAllClients();
    }

    public void saveClient(Client client) {
        jdbcClientDAO.saveClient(client);
    }

    public String updateClient(Client client) {
        if (jdbcClientDAO.findClientByEmail(client.getEmail()) == null) {
            return "Customer does not exist, update failed.";
        } else {
            jdbcClientDAO.updateClient(client);
            return "Update successful.";
        }
    }

    public List<Client> findClientByLastName(String lastName) {
        return jdbcClientDAO.findClientByLastName(lastName);
    }


}