package com.niantic.services;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public TransactionDao(){
        String databaseUrl = "jdbc:mysql://localhost:3306/budget";
        String userName = "root";
        String password = "P@ssword";
        DataSource datasource = new BasicDataSource(){{
            setUrl(databaseUrl);
            setUsername(userName);
            setPassword(password);
        }};
        jdbcTemplate = new
        JdbcTemplate(dataSource);
    }
// + getTransactionByUser(userId: int): ArrayList<Transaction>
 public ArrayList<Transaction> getTransactionByUser (int userId){

 }
// +getTransactionByMonth(month: int): ArrayList<Transaction>

// + getTransactionByYear(year: int): ArrayList<Transaction>

// + getTransactionByCategory(categoryId: int): ArrayList<Transaction>

// +getTransactionById(transactionId: int): Transaction

// +addTransaction(transaction: Transaction): void

// + updateTransaction(transaction: Transaction): void

// + deleteTransaction(transactionId: int): void

}
