package com.niantic.services;
import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.models.User;
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

@Component
public class VendorDao {
    private final JdbcTemplate jdbcTemplate;

    public VendorDao()
    {
        String databaseUrl = "jdbc:mysql://localhost:3306/budget";
        String userName = "root";
        String password = "P@ssw0rd";
        DataSource dataSource = new BasicDataSource(){{
            setUrl(databaseUrl);
            setUsername(userName);
            setPassword(password);
        }};

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    @Autowired
//    public VendorDao(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//    @Autowired
//    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
//    @Autowired
//    private CategoryDao categoryDao;
//    @Autowired
//    private VendorDao vendorDao;


    //getAllVendor
    public ArrayList<Vendor> getAllVendors()
    {
        ArrayList<Vendor> vendors = new ArrayList<>();

        String sql = """
                SELECT vendor_id
                    , vendor_name
                    , website
                FROM vendors;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while (row.next()) {
            int vendorId = row.getInt("vendor_id");
            String vendorName = row.getString("vendor_name");
            String website = row.getString("website");

            Vendor vendor = new Vendor(vendorId, vendorName, website);
            vendors.add(vendor);
        }
        return vendors;
    }

    //getVendorById
    public Vendor getVendorById(int vendorId)
    {
        Vendor vendor = new Vendor();

        String sql = """
                SELECT vendor_id
                    , vendor_name
                    , website
                FROM vendors
                WHERE vendor_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, vendorId);

        while (row.next()) {
            String vendorName = row.getString("vendor_name");
            String website = row.getString("website");

            vendor = new Vendor(vendorId, vendorName, website);
        }
        return vendor;
    }

    //getVendorByName
    public Vendor getVendorByName(String searchVendor)
    {
        searchVendor = "%" + searchVendor + "%";

        Vendor vendor = new Vendor();

        String sql = """
                SELECT vendor_id
                    , vendor_name
                    , website
                FROM vendors
                WHERE vendor_name LIKE ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, searchVendor);

        while (row.next()) {
            int vendorId = row.getInt("vendor_id");
            String vendorName = row.getString("vendor_name");
            String website = row.getString("website");

            vendor = new Vendor(vendorId, vendorName, website);
        }

        return vendor;
    }

    // Add vendor
    public void addVendor(Vendor vendor)
    {
        String sql = """
                INSERT INTO vendors (vendor_id, vendor_name, website)
                VALUES (?,?,?);
                """;

        jdbcTemplate.update(sql
                , vendor.getVendorId()
                , vendor.getVendorName()
                , vendor.getWebsite());
    }

    // Update vendor
    public void updateVendor(Vendor vendor)
    {
        String sql = """
                UPDATE vendors
                SET vendor_name = ?
                    , website = ?
                WHERE vendor_id = ?;
                """;

        jdbcTemplate.update(sql
                , vendor.getVendorName()
                , vendor.getWebsite()
                , vendor.getVendorId());
    }

    // + delete vendor
    public void deleteVendor(int vendorId)
    {
        String sql = """
                DELETE FROM vendors
                WHERE vendor_id = ?
                """;

        jdbcTemplate.update(sql, vendorId);
    }

}
