package com.niantic.services;

import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.models.Category;
import com.niantic.models.Vendor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;

//create DAO like this
@Component
public class TransactionDao
{
    private final JdbcTemplate jdbcTemplate;

//    public TransactionDao()
//    {
//        String databaseUrl = "jdbc:mysql://localhost:3306/budget";
//        String userName = "root";
//        String password = "P@ssw0rd";
//        DataSource dataSource = new BasicDataSource(){{
//            setUrl(databaseUrl);
//            setUsername(userName);
//            setPassword(password);
//        }};
//
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Autowired
    public TransactionDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
//    @Autowired
////    private UserDao userDao;
//    @Autowired
//    private CategoryDao categoryDao;
//    @Autowired
//    private VendorDao vendorDao;


    // + getTransactionByUser(userId: int): ArrayList<Transaction>
     public ArrayList<Transaction> getTransactionByUser (int userId)
     {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE user_id = ?;
                """;

         SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);

         while(row.next())
         {
             int transactionId = row.getInt("transaction_id");
             int categoryId = row.getInt("category_id");
             int vendorId = row.getInt("vendor_id");
             LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
             double amount = row.getDouble("amount");
             String notes = row.getString("notes");

             Transaction transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);
             transactions.add(transaction);
         }
         return transactions;
     }

// +getTransactionByMonth(month: int): ArrayList<Transaction>
    public ArrayList<Transaction> getTransactionByMonth(String month)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        month = "%-" + month + "-%";

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE transaction_date LIKE ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, month);

        while(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            Transaction transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);
            transactions.add(transaction);
        }
        return transactions;
    }

// + getTransactionByYear(year: int): ArrayList<Transaction>
    public ArrayList<Transaction> getTransactionByYear(String year)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        year = year + "-%";

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE transaction_date LIKE ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, year);

        while(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            Transaction transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);
            transactions.add(transaction);
        }
        return transactions;
    }

// + getTransactionByCategory(categoryId: int): ArrayList<Transaction>
    public ArrayList<Transaction> getTransactionByCategory(int categoryId)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE category_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, categoryId);

        while(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            Transaction transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);
            transactions.add(transaction);
        }
        return transactions;
    }

    public ArrayList<Transaction> getTransactionByVendor(int vendorId)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE vendor_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, vendorId);

        while(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            Transaction transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);
            transactions.add(transaction);
        }
        return transactions;

    }


// +getTransactionById(transactionId: int): Transaction
    public Transaction getTransactionById(int transactionId)
    {
        Transaction transaction = new Transaction();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE transaction_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, transactionId);

        while(row.next())
        {
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);

        }
        return transaction;
    }

// +addTransaction(transaction: Transaction): void
    public void addTransaction(Transaction transaction)
    {
        String sql = """
                INSERT INTO transactions (transaction_id, user_id, category_id, vendor_id, transaction_date, amount, notes)
                VALUES (?,?,?,?,?,?,?);
                """;

        jdbcTemplate.update(sql
                , transaction.getTransactionId()
                , transaction.getUserId()
                , transaction.getCategoryId()
                , transaction.getVendorId()
                , transaction.getTransactionDate()
                , transaction.getAmount()
                , transaction.getNotes());
    }

// + updateTransaction(transaction: Transaction): void
    public void updateTransaction(Transaction transaction)
    {
        String sql = """
                UPDATE transactions
                SET user_id = ?
                    , category_id = ?
                    , vendor_id = ?
                    , transaction_date = ?
                    , amount = ?
                    , notes = ?
                WHERE transaction_id = ?;
                """;

        jdbcTemplate.update(sql
                , transaction.getUserId()
                , transaction.getCategoryId()
                , transaction.getVendorId()
                , transaction.getTransactionDate()
                , transaction.getAmount()
                , transaction.getNotes()
                , transaction.getTransactionId());
    }

// + deleteTransaction(transactionId: int): void
    public void deleteTransaction(int transactionId)
    {
        String sql = """
                DELETE FROM transactions
                WHERE transaction_id = ?
                """;

        jdbcTemplate.update(sql, transactionId);
    }

    public ArrayList<Transaction> getAllTransactions()
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            Transaction transaction = new Transaction(transactionId, userId, categoryId, vendorId, transactionDate, amount, notes);
            transactions.add(transaction);
        }
        return transactions;
    }
    public ArrayList<String> getTransactionReports()
    {
        ArrayList<String> reports = new ArrayList<>();

        reports.add("get Transaction By Category");
        reports.add("get Transaction By Id");


    return reports;
    }

//    public ArrayList<Transaction> getTransactionByUser()
//    {
//
//    }


}
