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

    @Autowired
    public VendorDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

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

}
