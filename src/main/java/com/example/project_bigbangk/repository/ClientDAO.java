// Created by vip
// Creation date 02/12/2021
package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClientDAO implements IClientDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveClient(Client mpClient){
        String sql = "Insert into Client values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                mpClient.getFirstName(),
                mpClient.getInsertion(),
                mpClient.getLastName(),
                mpClient.getEmail(),
                mpClient.getBsn(),
                mpClient.getDateOfBirth(),
                mpClient.getPassWord(),
                mpClient.getAddress(),
                mpClient.getWallet());
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