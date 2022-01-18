package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Orders.Limit_Buy;
import com.example.project_bigbangk.model.Orders.Limit_Sell;
import com.example.project_bigbangk.model.Orders.Transaction;
import com.example.project_bigbangk.model.Orders.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * Created by Vanessa Philips.
 * DAO for orders, typified by "Transaction", "Limit_sell", "Limit_buy", "Stoploss_sell".
 */

@Repository
public class JdbcOrderDAO {

    private final Logger logger = LoggerFactory.getLogger(JdbcOrderDAO.class);
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdbcOrderDAO");
    }

    //Transaction
    public void saveTransaction(Transaction transaction){
        String sql = "INSERT INTO bigbangk.order (buyer, seller, code, type, amount, date, fee, totalprice) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            jdbcTemplate.update(sql,
                    transaction.getBuyerWallet().getIban(),
                    transaction.getSellerWallet().getIban(),
                    transaction.getAsset().getCode(),
                    TransactionType.TRANSACTION,
                    transaction.getNumberOfAssets(),
                    java.sql.Timestamp.valueOf(transaction.getDate()),
                    transaction.getTransactionFee(),
                    transaction.getRequestedPrice());

        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
    }

    public Transaction findTransactionById(Long orderId){
        String sql = "SELECT * FROM Order WHERE id=?;";
        try {
            return jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), orderId);
        } catch (DataAccessException dataAccessException) {
            System.err.println(dataAccessException.getMessage());
        }
        return null;
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            int orderId = resultSet.getInt("orderId");
            Double requestedPrice = resultSet.getDouble("requestedPrice");
            Integer numberOfAssets = resultSet.getInt("numberOfAssets");
            LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);
            Double transactionFee = resultSet.getDouble("transactionFee");
            return new Transaction(orderId, requestedPrice, numberOfAssets, date, transactionFee);
        }
    }

    //TODO:
    //nadenken over wat we eigelijk willen doen met deze methodes! ik denk bv dat je misschien als gebruiker all je orders wil zien, of alle orders van een type.
    //in sprint 3 moeten we misschien controlleren op alle triggerorders die open staan voor een asset.
//    public List<> findAllOrdersByType(){
//        String sql = "SELECT * FROM Order WHERE type = ?;";
//        try {
//            return jdbcTemplate.query(sql, new JdbcOrderDAO.TransactionRowMapper());
//        } catch (DataAccessException dataAccessException) {
//            System.err.println(dataAccessException.getMessage());
//        }
//        return null;
//    }

    //Limit_buy
    /**
     * Saves Limit_Buy order in database, waiting to be matched with another client's offer (matchservice).
     * @param limit_buy
     * author = Vanessa Philips
     */
    public void saveLimit_Buy(Limit_Buy limit_buy){
        String sql = "INSERT INTO bigbangk.order (buyer, code, type, orderlimit, amount, date) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            jdbcTemplate.update(sql,
                    limit_buy.getBuyerWallet().getIban(),
                    limit_buy.getAsset().getCode(),
                    TransactionType.LIMIT_BUY,
                    limit_buy.getRequestedPrice(),
                    limit_buy.getNumberOfAssets(),
                    java.sql.Timestamp.valueOf(limit_buy.getDate()));
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
    }

    //Limit_sell

    //TODO graag Limit_Sell methode reviewen.

    /**
     * Saves Limit_Sell order in database, waiting to be matched with another client's offer (matchservice).
     * @param limit_sell
     * author = Vanessa Philips
     */
    public void saveLimit_Sell(Limit_Sell limit_sell){
        String sql = "INSERT INTO bigbangk.order (seller, code, type, orderlimit, amount, date) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            jdbcTemplate.update(sql,
                    limit_sell.getSellerWallet().getIban(),
                    limit_sell.getAsset().getCode(),
                    TransactionType.LIMIT_SELL,
                    limit_sell.getRequestedPrice(),
                    limit_sell.getNumberOfAssets(),
                    java.sql.Timestamp.valueOf(limit_sell.getDate()));
        } catch (DataAccessException dataAccessException) {
            logger.info(dataAccessException.getMessage());
        }
    }

    //TODO Stoploss_sell toevoegen

    //Stoploss_sell


}