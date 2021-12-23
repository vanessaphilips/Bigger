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

    private PreparedStatement insertTransactionStatement(Transaction transaction, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Order (requestedPrice, numberOfAssets, date, transactionFee) " +
                        "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, transaction.getRequestedPrice());
        ps.setInt(2, transaction.getNumberOfAssets());
        ps.setObject(3, transaction.getDate(LocalDateTime.class));
        ps.setDouble(4, transaction.getTransactionFee());
        return ps;
    }

    public void saveTransaction(Transaction transaction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> insertTransactionStatement(transaction, connection), keyHolder);
        long newKey = keyHolder.getKey().longValue();
        transaction.setOrderId(newKey);
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

    public List<Transaction> findAllTransactions(){
            String sql = "SELECT * FROM Order WHERE type = transaction ;";
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