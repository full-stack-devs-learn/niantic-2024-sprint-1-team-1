package com.niantic.services;
import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class CategoryDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
//    @Autowired
//    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
//    @Autowired
//    private UserDao userDao;
    @Autowired
    private VendorDao vendorDao;

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = """
                SELECT category_id
                     , category_name
                     , description
                FROM categories;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while (row.next()) {
            int categoryId = row.getInt("category_id");
            String categoryName = row.getString("category_name");
            String description = row.getString("description");

            Category category = new Category(categoryId, categoryName, description);
            categories.add(category);
        }
        return categories;
    }

    public Category getCategoryById(int categoryId) {
        Category category = new Category();
        String sql = """
                SELECT category_id
                     , category_name
                     , description
                FROM categories
                WHERE category_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, categoryId);

        while (row.next()) {
            String categoryName = row.getString("category_name");
            String description = row.getString("description");

            category = new Category(categoryId, categoryName, description);
        }
        return category;


    }
    public ArrayList<Category> getCategoryByName(String searchCategory) {
        searchCategory = "%" + searchCategory + "%";
        var categories = new ArrayList<Category>();
        String sql = """
                SELECT category_id
                     ,category_name
                     , description
                FROM categories
                WHERE category_name
                LIKE ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, searchCategory);

        while (row.next()) {
            String categoryName = row.getString("category_name");
            int categoryId =row.getInt("category_id");
            String description = row.getString("description");

            var category = new Category(categoryId, categoryName, description);
            categories.add(category);
        }
        return categories;
    }

    // Add Category
    public void addCategory(Category category)
    {
        String sql = """
                INSERT INTO categories (category_id, category_name, description)
                VALUES (?,?,?);
                """;

        jdbcTemplate.update(sql
                , category.getCategoryId()
                , category.getCategoryName()
                , category.getDescription());
    }

    // Update category
    public void updateCategory(Category category)
    {
        String sql = """
                UPDATE categories
                SET category_name = ?
                    , description = ?
                WHERE category_id = ?;
                """;

        jdbcTemplate.update(sql
                , category.getCategoryName()
                , category.getDescription()
                , category.getCategoryId());
    }

    // + delete category
    public void deleteCategory(int categoryId)
    {
        String sql = """
                DELETE FROM categories
                WHERE category_id = ?
                """;

        jdbcTemplate.update(sql, categoryId);
    }
}
