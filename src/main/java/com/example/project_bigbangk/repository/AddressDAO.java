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
public class AddressDAO implements IAddressDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(Address mpAddress) {
        String sql = "Insert into Address values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, mpAddress.getPostalCode(), mpAddress.getStreet(),mpAddress.getNumber(),
                mpAddress.getCity(), mpAddress.getCountry(), mpAddress.getEmail());
    }

    @Override
    public Address findAddressByEmail(String email) {
        String sql = "Select * From Address Where email = ?";
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
    public void update(Address address) {
        String sql = "Update address Set postalcode = ?, street = ?, " +
                "number = ?, city = ?, country = ? where email = ?;";
        jdbcTemplate.update(sql, address.getPostalCode(),
                address.getStreet(), address.getNumber(),
                address.getCity(), address.getCountry(),
                address.getEmail());
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
                    resultSet.getString("country"), resultSet.getString("email"));
        }
    }





}