package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Orders.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

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
//    private PreparedStatement insertTransactionStatement(Transaction transaction, Connection connection) throws SQLException {
//        PreparedStatement ps = connection.prepareStatement(
//                "INSERT INTO Order (buyer, seller, code, type, amount, date, fee, totalprice) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
//                , Statement.RETURN_GENERATED_KEYS);
//        ps.setString(1, transaction.getBuyerWallet().getIban());
//        ps.setString(2, transaction.getSellerWallet().getIban());
//        ps.setString(3, transaction.getAsset().getCode());
//        ps.setString(4, "transaction");
//        ps.setDouble(5, transaction.getNumberOfAssets());
//        ps.setTimestamp(6, java.sql.Timestamp.valueOf(transaction.getDate()));
//        ps.setDouble(7, transaction.getTransactionFee());
//        ps.setDouble(8, transaction.getRequestedPrice());
//        return ps;
//    }

//    public long saveTransaction(Transaction transaction) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        String sql = "INSERT INTO order (buyer, seller, code, type, amount, date, fee, totalprice) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection
//                    .prepareStatement(sql);
//            ps.setString(1, transaction.getBuyerWallet().getIban());
//            ps.setString(2, transaction.getSellerWallet().getIban());
//            ps.setString(3, transaction.getAsset().getCode());
//            ps.setString(4, "transaction");
//            ps.setDouble(5, transaction.getNumberOfAssets());
//            ps.setTimestamp(6, java.sql.Timestamp.valueOf(transaction.getDate()));
//            ps.setDouble(7, transaction.getTransactionFee());
//            ps.setDouble(8, transaction.getRequestedPrice());
//            return ps;
//        }, keyHolder);
//
//        return (long) keyHolder.getKey();
//    }

    public void saveTransaction(Transaction transaction){
        String sql = "INSERT INTO bigbangk.order (buyer, seller, code, type, amount, date, fee, totalprice) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            jdbcTemplate.update(sql,
                    transaction.getBuyerWallet().getIban(),
                    transaction.getSellerWallet().getIban(),
                    transaction.getAsset().getCode(),
                    "transaction",
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

    //nadenken over wat we eigelijk willen doen met deze methodes! ik denk bv dat je misschien als gebruiker all je orders wil zien, of alle orders van een type.
    //in sprint 3 moeten we misschien controlleren op alle triggerorders die open staan voor een asset.
    public List<Transaction> findAllTransactions(){
            String sql = "SELECT * FROM Order WHERE type = transaction;";
            try {
                return jdbcTemplate.query(sql, new JdbcOrderDAO.TransactionRowMapper());
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

    //TODO overige ordertypes toevoegen
    // Wellicht hier ook "FindOrdeByType" toevoegen?

    //Limit_sell

    //Limit_buy

    //Stoploss_sell


}