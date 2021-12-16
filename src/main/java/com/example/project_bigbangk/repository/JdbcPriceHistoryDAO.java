// Created by Deek
// Creation date 12/14/2021

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.AssetCode_Name;
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
public class JdbcPriceHistoryDAO implements IPriceHistoryDAO {

    private final Logger logger = LoggerFactory.getLogger(JdbcPriceHistoryDAO.class);
    JdbcTemplate jdbcTemplate;

    public JdbcPriceHistoryDAO(JdbcTemplate jdbcTemplate) {
        super();
        logger.info("New JdbcPriceHistoryDAO");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void savePriceHistory(PriceHistory priceHistory) {
        String sql = "Insert into PriceHistory values(?,?,?);";
        try {
            jdbcTemplate.update(sql,
                    priceHistory.getDateTime(),
                    priceHistory.getAsset().getAssetCodeName().getAssetCode(),
                    priceHistory.getPrice());
        } catch (DataAccessException dataAccessException) {
           logger.info(dataAccessException.getMessage());
        }
    }

    @Override
    public double getCurrentPriceByAssetCodeName(AssetCode_Name assetCodeName){
        String sql = "Select * from priceHistory where datetime in (SELECT max(datetime) FROM pricehistory ) and code = ? ;";
        double currentprice = 0;
        try {
            PriceHistory priceHistory = jdbcTemplate.queryForObject(sql,
                    new PiceHistoryRowMapper(), assetCodeName.getAssetCode());
            currentprice = priceHistory.getPrice();
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
        return currentprice;
    }

    private class PiceHistoryRowMapper implements RowMapper<PriceHistory> {
        @Override
        public PriceHistory mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            LocalDate localDate = resultSet.getDate("datetime").toLocalDate();
            LocalDateTime localDateTime = localDate.atTime(resultSet.getTime("datetime").toLocalTime());
            return new PriceHistory(
                    localDateTime, resultSet.getDouble("price"));
        }
    }
}