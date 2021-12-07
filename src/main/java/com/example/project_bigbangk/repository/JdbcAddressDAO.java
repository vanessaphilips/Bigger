// Created by RayS
// Creation date 2-12-2021

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAddressDAO implements IAddressDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAddressDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(Address mpAddress) {
        String sql = "Insert into Address values(?,?,?,?,?)";
        jdbcTemplate.update(sql, mpAddress.getPostalCode(), mpAddress.getStreet(),mpAddress.getNumber(),
                mpAddress.getCity(), mpAddress.getCountry());
    }

    @Override
    public Address findAddressByEmail(String email) {
        String sql = "Select * From Address Where postalcode = ? AND number = ?"; //icm SQL syntax (join) oplossen
        Address address;
        try {
            address = jdbcTemplate.queryForObject(sql, new AddressRowMapper(), email);
        } catch (EmptyResultDataAccessException noResult) {
            address = null;
        }
        return address;
    }

    @Override
    public List<Address> findAllAddresses() {
        String sql = "Select * From address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    @Override
    public List<Address> findAddressByPostalcode(String postalcode) {
        String sql = "Select * From address Where postalcode = ?";
        return jdbcTemplate.query(sql, new AddressRowMapper(), postalcode);
    }

    private class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Address(resultSet.getString("postalcode"), resultSet.getString("street"),
                    resultSet.getInt("number"), resultSet.getString("city"),
                    resultSet.getString("country"));
        }
    }

}