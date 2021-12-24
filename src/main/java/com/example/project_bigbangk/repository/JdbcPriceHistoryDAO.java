// Created by Deek
// Creation date 12/14/2021

package com.example.project_bigbangk.repository;

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
import java.util.List;

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
                    priceHistory.getAsset().getCode(),
                    priceHistory.getPrice());
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
    }

    @Override
    public double getCurrentPriceByAssetCode(String assetCode) {
        String sql = "Select * from (SELECT * FROM pricehistory where code = ? )as priceHisotryByCoin ORDER BY dateTime DESC LIMIT 1;";
        double currentPrice = -1;
        try {
            PriceHistory priceHistory = jdbcTemplate.queryForObject(sql,
                    new PriceHistoryRowMapper(), assetCode);
            currentPrice = priceHistory==null?-1:  priceHistory.getPrice();
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
        return currentPrice;
    }

    @Override
    public List<PriceHistory> getPriceHistoriesByCodeFromDate(LocalDateTime date, String assetCode) {
        String sql = "SELECT * FROM pricehistory where dateTime> ? and code = ?;";
        List<PriceHistory> priceHistories = null;
        try {
            priceHistories = jdbcTemplate.query(sql, new PriceHistoryRowMapper(), date, assetCode);
            if(priceHistories.size()==0){
                priceHistories = null;
            }
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
        return priceHistories;
    }

    private class PriceHistoryRowMapper implements RowMapper<PriceHistory> {
        @Override
        public PriceHistory mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            LocalDate localDate = resultSet.getDate("datetime").toLocalDate();
            LocalDateTime localDateTime = localDate.atTime(resultSet.getTime("datetime").toLocalTime());
            return new PriceHistory(
                    localDateTime, resultSet.getDouble("price"));
        }
    }
}