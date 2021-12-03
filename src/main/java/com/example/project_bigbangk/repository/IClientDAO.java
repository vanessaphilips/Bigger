// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import java.util.List;

public interface IClientDAO {

    public void saveClient(Client mpClient);

    public Client findClientByEmail(String email);

    public List<Client> findAllClients();

    public void updateClient(Client client);

    public List<Client> findClientByLastName(String lastName);

}
