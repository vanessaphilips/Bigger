//package com.example.project_bigbangk.repository;
//
//import com.example.project_bigbangk.model.Asset;
//import com.example.project_bigbangk.model.Transaction;
//import com.example.project_bigbangk.model.Wallet;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.util.List;
//
///**
// * Created by Vanessa Philips.
// * DAO for transactions/orders, typified by "Transaction", "Limit_sell", "Limit_buy", "Stoploss_sell".
// */
//
//@Repository
//public class JdbcOrderDAO {
//
//    private final Logger logger = LoggerFactory.getLogger(JdbcOrderDAO.class);
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcOrderDAO(JdbcTemplate jdbcTemplate) {
//        super();
//        this.jdbcTemplate = jdbcTemplate;
//        logger.info("New JdbcTransactionDAO");
//    }
//
//    //Transaction
//
//    private PreparedStatement insertTransactionStatement(Transaction transaction, Connection connection) throws SQLException {
//        PreparedStatement ps = connection.prepareStatement(
//                "INSERT INTO Order (asset, requestedPrice, numberOfAssets, date, transactionFee, buyer, seller) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//        ps.setObject(1, transaction.getAsset());
//        ps.setDouble(2, transaction.getRequestedPrice());
//        ps.setInt(3, transaction.getNumberOfAssets());
//        ps.setDate(4, transaction.getDate());
//        ps.setDouble(5, transaction.getTransactionFee());
//        ps.setObject(6, transaction.getBuyerWallet());
//        ps.setObject(7, transaction.getSellerWallet());
//        return ps;
//    }
//
//    public void saveTransaction(Transaction transaction) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(connection -> insertTransactionStatement(transaction, connection), keyHolder);
//        long newKey = keyHolder.getKey().longValue();
//        transaction.setOrderId(newKey);
//    }
//
//    public Transaction findTransactionById(Long orderId){
//        String sql = "SELECT * FROM Order WHERE id=?;";
//        try {
//            return jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), orderId);
//        } catch (DataAccessException dataAccessException) {
//            System.err.println(dataAccessException.getMessage());
//        }
//        return null;
//    }
//
//    //TODO hoe zorg ik er bij onderstaande voor dat ik alleen de "transactions" meeneem (dus van het type transaction)??
//
//    public List<Transaction> findAllTransactions(){
//            String sql = "SELECT * FROM Order WHERE XXXXX ;";
//            try {
//                return jdbcTemplate.query(sql, new JdbcOrderDAO.TransactionRowMapper());
//            } catch (DataAccessException dataAccessException) {
//                System.err.println(dataAccessException.getMessage());
//            }
//            return null;
//        }
//
//        // TODO wat gaat er mis in onderstaande bij <new Transaction>???
//
//    private static class TransactionRowMapper implements RowMapper<Transaction> {
//
//        @Override
//        public Transaction mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//            long orderId = resultSet.getInt("orderId");
//            Asset asset = (Asset)resultSet.getObject("asset");
//            Double requestedPrice = resultSet.getDouble("requestedPrice");
//            Integer numberOfAssets = resultSet.getInt("numberOfAssets");
//            LocalDateTime date = resultSet.getObject("date", LocalDateTime.class);
//            Double transactionFee = resultSet.getDouble("transactionFee");
//            Wallet buyerWallet = (Wallet)resultSet.getObject("buyerWallet");
//            Wallet sellerWallet = (Wallet)resultSet.getObject("sellerWallet");
//            Transaction transaction = new Transaction(asset, requestedPrice, numberOfAssets,
//                    date, transactionFee, buyerWallet, sellerWallet);
//            transaction.setOrderId(orderId);
//            return transaction;
//        }
//    }
//
//    //TODO overige transactietypes toevoegen
//    // Wellicht hier ook "FindOrdeByType" toevoegen?
//
//    //Limit_sell
//
//    //Limit_buy
//
//    //Stoploss_sell
//
//
//}