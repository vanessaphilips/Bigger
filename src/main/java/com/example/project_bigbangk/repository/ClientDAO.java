// Created by vip
// Creation date 02/12/2021

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAO implements IClientDAO{

    private final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

    public ClientDAO() {
        super();
        logger.info("New ClientDAO");
    }

    @Override
    public void saveClient(Client mpClient){
    }

    @Override
    public Client findClientByEmail(int email){
        return null;
    }

    @Override
    public List<Client> findAllClients(){
        return null;
    }

    @Override
    public void updateClient(Client client){

    }

    @Override
    public List<Client> findClientByLastName(String lastName){
        return null;
    }

}