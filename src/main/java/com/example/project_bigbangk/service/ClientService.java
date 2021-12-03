// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.repository.ClientDAO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private ClientDAO clientDAO;
    private RootRepository rootRepository;

    public ClientService(ClientDAO clientDAO, RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        this.clientDAO = clientDAO;
    }

    // TODO de volgende methode actief maken wanneer de RootRepository gereed is.

//    public Client getClientByEmail(String email) {
//        return rootRepository.findClientByEmail(email);
//    }

    public List<Client> getAllClients() {
        return clientDAO.findAllClients();
    }

    public void saveClient(Client client) {
        clientDAO.saveClient(client);
    }

    public String updateClient(Client client) {
        if (clientDAO.findClientByEmail(client.getEmail()) == null) {
            return "Customer does not exist, update failed.";
        } else {
            clientDAO.updateClient(client);
            return "Update successful.";
        }
    }

    public List<Client> findClientByLastName(String lastName) {
        return clientDAO.findClientByLastName(lastName);
    }

}