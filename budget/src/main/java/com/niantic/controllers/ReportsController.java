package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.models.Vendor;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import com.niantic.services.UserDao;
import com.niantic.services.VendorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

@Controller
public class ReportsController
{
//    private CategoryDao categoryDao = new CategoryDao();
//    private TransactionDao transactionDao = new TransactionDao();
//    private UserDao userDao = new UserDao();
//    private VendorDao vendorDao = new VendorDao();

// use this when accessing dao in another controller autowired
    @Autowired
    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private VendorDao vendorDao;

    @GetMapping("/reports/index")
        public String getAllReports(Model model, @RequestParam(required = false) Integer categoryId
                                    , @RequestParam(required = false)Integer userId
        ) {
            ArrayList<Transaction> transactions = new ArrayList<>();
            if(categoryId != null)
            {
                //transactions = transactionDao.getTransactionByCategory(category);
            }
            else if(userId != null)
            {
                transactions = transactionDao.getTransactionByUser(userId);
            }
            else {

                transactions = transactionDao.getAllTransactions();
            }

            model.addAttribute("transactions", transactions);
            return "reports/index";
        }

    @GetMapping("reports/byuser")
    public String getTransactionsByUser(Model model, @RequestParam(required = false, defaultValue = "0") int userId)
    {
        ArrayList<User> users = userDao.getAllUsers();

        model.addAttribute("users", users);

        ArrayList<Transaction> transactions = new ArrayList<>();

        if(userId == 0)
        {
            transactions = transactionDao.getAllTransactions();
        }
        else
        {
            transactions = transactionDao.getTransactionByUser(userId);
        }

        model.addAttribute("transactions", transactions);

        return "reports/byuser";
    }

    // Transactions by category
    @GetMapping("reports/bycategory")
    public String getTransactionsByCategory(Model model, @RequestParam(required = false, defaultValue = "0") int categoryId)
    {
        ArrayList<Category> categories = categoryDao.getAllCategories();

        model.addAttribute("categories", categories);

        ArrayList<Transaction> transactions = new ArrayList<>();

        if(categoryId == 0)
        {
            transactions = transactionDao.getAllTransactions();
        }
        else
        {
            transactions = transactionDao.getTransactionByCategory(categoryId);
        }

        model.addAttribute("transactions", transactions);

        return "reports/bycategory";
    }

//    @GetMapping("reports/by_user/{id}")
//    public String getTransactionsByUser(Model model, @PathVariable int id)
//    {
//        ArrayList<Transaction> transactions;
//
//        transactions = transactionDao.getTransactionByUser(id);
//
//        model.addAttribute("transactions", transactions);
//
//        return "reports/by_user";
//
//    }

    // Transactions by vendor
    @GetMapping("reports/byvendor")
    public String getTransactionsByVendor(Model model, @RequestParam(required = false, defaultValue = "0") int vendorId)
    {
        ArrayList<Vendor> vendors = vendorDao.getAllVendors();

        model.addAttribute("vendors", vendors);

        ArrayList<Transaction> transactions = new ArrayList<>();

        if(vendorId == 0)
        {
            transactions = transactionDao.getAllTransactions();
        }
        else
        {
            transactions = transactionDao.getTransactionByVendor(vendorId);
        }

        model.addAttribute("transactions", transactions);

        return "reports/byvendor";
    }

    // Transactions by Month
    @GetMapping("reports/bymonth")
    public String getTransactionsByMonth(Model model, @RequestParam(required = false, defaultValue = "0") String month)
    {
        ArrayList<Transaction> transactions;

        int monthNumber = Integer.parseInt(month);

        if(monthNumber == 0)
        {
            transactions = transactionDao.getAllTransactions();
        }
        else
        {
            transactions = transactionDao.getTransactionByMonth(month);
        }

        model.addAttribute("transactions", transactions);

        return "reports/bymonth";

    }

    // Transactions by Year
    @GetMapping("reports/byyear")
    public String getTransactionsByYear(Model model, @RequestParam(required = false, defaultValue = "0") String year)
    {
        ArrayList<Transaction> transactions;

        int yearNumber = Integer.parseInt(year);

        if(yearNumber == 0)
        {
            transactions = transactionDao.getAllTransactions();
        }
        else
        {
            transactions = transactionDao.getTransactionByYear(year);
        }

        model.addAttribute("transactions", transactions);

        return "reports/byyear";

//        ArrayList<Transaction> transactions;
//
//        transactions = transactionDao.getTransactionByYear(year);
//
//        model.addAttribute("transactions", transactions);
//
//        return "reports/by_year";

    }


}
