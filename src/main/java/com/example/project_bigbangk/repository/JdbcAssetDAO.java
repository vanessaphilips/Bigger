// Created by Deek
// Creation date 12/14/2021

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.PriceHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public class JdbcAssetDAO implements IAssetDAO {

    private final Logger logger = LoggerFactory.getLogger(JdbcAssetDAO.class);
    JdbcTemplate jdbcTemplate;

    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        super();
        logger.info("New JdbcAssetDAO");
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveAsset(Asset asset) {
        String sql = "Insert into Asset values(?,?);";
        try {// public Asset(String name, String code, double currentPrice)
            jdbcTemplate.update(sql, asset.getCode(),
                    asset.getName()
            );
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
    }

    @Override
    public int getAmountOfAssets() {
        String sql = "SELECT count(code) FROM Asset";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);

        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
        return 0;
    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

            return new Asset(resultSet.getString("name"), resultSet.getString("code"));
        }
    }
}
