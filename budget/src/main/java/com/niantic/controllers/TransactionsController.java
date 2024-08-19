package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import com.niantic.services.UserDao;
import com.niantic.services.VendorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class TransactionsController
{


    @Autowired
    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private VendorDao vendorDao;


    @GetMapping("/transactions/index")
    public String getAllTransactions(Model model, @RequestParam(required = false) String transaction) {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getAllTransactions();

        model.addAttribute("transactions", transactions);

        return "transactions/index";
    }


    @GetMapping("/transactions/reports")
    public String getTransactionReports(Model model, @RequestParam(required = false) String transactions)
    {
        ArrayList<String> reports;

        reports = transactionDao.getTransactionReports();

        model.addAttribute("transactions", transactions);

        return "transactions/reports";
    }

    //Add Transaction
    @GetMapping("transactions/add")
    public String addTransaction(Model model)
    {
        ArrayList<User> users = userDao.getAllUsers();

        model.addAttribute("transaction", new Transaction());
        model.addAttribute("users", users);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("vendors", vendorDao.getAllVendors());
        model.addAttribute("action", "add");

        return "transactions/add_edit";
    }

    @PostMapping("transactions/add")
    public String addTransaction(Model model, @ModelAttribute("transaction") Transaction transaction)
    {
        transactionDao.addTransaction(transaction);
        model.addAttribute("transaction", transaction);

        return "transactions/add_success";
    }

    // Edit Transaction
    @GetMapping("transactions/{id}/edit")
    public String editTransaction(Model model, @PathVariable int id)
    {
        Transaction transaction = transactionDao.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        model.addAttribute("users", userDao.getAllUsers());
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("vendors", vendorDao.getAllVendors());
        model.addAttribute("action", "edit");

        return "transactions/add_edit";
    }

    @PostMapping("transactions/{id}/edit")
    public String editTransaction(@ModelAttribute("transaction") Transaction transaction, @PathVariable int id)
    {
        transaction.setTransactionId(id);
        transactionDao.updateTransaction(transaction);

        return "redirect:/transactions/index";
    }

    // Delete Transaction
    @GetMapping("transactions/{id}/delete")
    public String deleteTransaction(Model model, @PathVariable int id)
    {
        Transaction transaction = transactionDao.getTransactionById(id);
        if(transaction == null)
        {
            model.addAttribute("message", String.format("Transaction not found for id %d", id));
            return "404";
        }

        model.addAttribute("transaction", transaction);
        return "transactions/delete";
    }

    @PostMapping("transactions/{id}/delete")
    public String deleteProduct(@PathVariable int id)
    {
        transactionDao.deleteTransaction(id);

        return "redirect:/transactions/index";
    }

}