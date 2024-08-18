package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import com.niantic.services.UserDao;
import com.niantic.services.VendorDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Transactions by User
//    @GetMapping("reports/{id}/by_user")
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

    // TEST 2
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


        System.out.println("Number of transactions for user " + userId + ": " + transactions.size());
        model.addAttribute("transactions", transactions);

        return "reports/byuser";
    }

    // TEST 1 NOT WORKING
//    @GetMapping("reports/byuser")
//    public String getReportByUsers(Model model, @RequestParam(required = false) String user)
//    {
//        ArrayList<Transaction> byuser = new ArrayList<>();
//
//        User searchUser = userDao.getUserByName(user);
//        int userId = searchUser.getUserId();
//
//        if(user == null)
//        {
//            byuser = transactionDao.getAllTransactions();
//        }
//        else
//        {
//            byuser = transactionDao.getTransactionByUser(userId);
//        }
//
//        model.addAttribute("byuser", byuser);
//
//        return "reports/byuser";
//    }

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

    // Transactions by category
    @GetMapping("reports/{id}/by_category")
    public String getTransactionsByCategory(Model model, @PathVariable int id)
    {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getTransactionByCategory(id);

        model.addAttribute("transactions", transactions);

        return "reports/by_category";

    }

    // Transactions by vendor
    @GetMapping("reports/{id}/by_vendor")
    public String getTransactionsByVendor(Model model, @PathVariable int id)
    {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getTransactionByVendor(id);

        model.addAttribute("transactions", transactions);

        return "reports/by_vendor";

    }

    // Transactions by Month
    @GetMapping("reports/{month}/by_month")
    public String getTransactionsByMonth(Model model, @PathVariable String month)
    {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getTransactionByMonth(month);

        model.addAttribute("transactions", transactions);

        return "reports/by_month";

    }

    // Transactions by Year
    @GetMapping("reports/{year}/by_year")
    public String getTransactionsYear(Model model, @PathVariable String year)
    {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getTransactionByYear(year);

        model.addAttribute("transactions", transactions);

        return "reports/by_year";

    }


}
