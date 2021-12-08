// Created by vip
// Creation date 02/12/2021
package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class JdbcClientDAO implements IClientDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveClient(Client mpClient){
        String sql = "Insert into Client values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, mpClient.getFirstName(), mpClient.getInsertion(),
                mpClient.getLastName(), mpClient.getEmail(), mpClient.getBsn(),
                mpClient.getDateOfBirth(), mpClient.getPassWord(),
                mpClient.getAddress(), mpClient.getWallet());
    }

    @Override
    public Client findClientByEmail(String email){
        String sql = "SELECT * FROM Client WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new ClientRowMapper(), email);
    }

    @Override
    public List<Client> findAllClients(){
        String sql = "SELECT * FROM Client";
        return jdbcTemplate.query(sql, new ClientRowMapper());
    }

    @Override
    public void updateClient(Client client){
        String sql = "UPDATE Client Set firstName = ?, insertion = ?, " +
                "lastName = ?, email = ?, bsn = ?, dateOfBirth = ?, " +
                "passWord = ?, address = ? wallet = ?, WHERE email = ?;";
        jdbcTemplate.update(sql, client.getFirstName(), client.getInsertion(),
                client.getLastName(), client.getEmail(), client.getBsn(),
                client.getDateOfBirth(), client.getPassWord(),
                client.getAddress(), client.getWallet());
    }

    @Override
    public List<Client> findClientByLastName(String lastName){
        String sql = "SELECT * FROM Client WHERE lastName = ?";
        return jdbcTemplate.query(sql, new ClientRowMapper(), lastName);
    }

    //FIXME in RowMapper alleen de attributen uit Client (dus niet Address en Wallet)

    private class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Client(resultSet.getString("firstName"),
                    resultSet.getString("insertion"),
                    resultSet.getString("lastName"),
                    resultSet.getString("email"),
                    resultSet.getString("bsn"),
                    resultSet.getDate("dateOfBirth"),
                    resultSet.getString("passWord"));
        }
    }
}