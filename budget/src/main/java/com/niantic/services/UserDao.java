package com.niantic.services;
import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.time.LocalDate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class UserDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
//    @Autowired
//    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
//    @Autowired
//    private CategoryDao categoryDao;
//    @Autowired
//    private VendorDao vendorDao;

    //getAllUser
    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> users = new ArrayList<>();

        String sql = """
                SELECT user_id
                    , user_name
                    , first_name
                    , last_name
                    , phone
                    , email
                FROM users;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while (row.next()) {
            int userId = row.getInt("user_id");
            String userName = row.getString("user_name");
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");
            String phone = row.getString("phone");
            String email = row.getString("email");

            User user = new User(userId, userName, firstName, lastName, phone, email);
            users.add(user);
        }
        return users;
    }

    //getUserById
    public User getUserById(int userId)
    {
        User user = new User();

        String sql = """
                SELECT user_id
                    , user_name
                    , first_name
                    , last_name
                    , phone
                    , email
                FROM users
                WHERE user_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);

        while (row.next()) {
            String userName = row.getString("user_name");
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");
            String phone = row.getString("phone");
            String email = row.getString("email");

            user = new User(userId, userName, firstName, lastName, phone, email);
        }
        return user;
    }


    // getUserByName
    public User getUserByName(String searchUser)
    {
        searchUser = "%" + searchUser + "%";

        User user = new User();

        String sql = """
                SELECT user_id
                    , user_name
                    , first_name
                    , last_name
                    , phone
                    , email
                FROM users
                WHERE user_name LIKE ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, searchUser);

        while (row.next()) {
            int userId = row.getInt("user_id");
            String userName = row.getString("user_name");
            String firstName = row.getString("first_name");
            String lastName = row.getString("last_name");
            String phone = row.getString("phone");
            String email = row.getString("email");

            user = new User(userId, userName, firstName, lastName, phone, email);
        }

        return user;
    }
}
