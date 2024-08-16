package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.font.TransformAttribute;
import java.util.ArrayList;

@Controller
public class TransactionsController {
    @Autowired
    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);

    @GetMapping("/transactions")
    public String getAllTransactions(Model model, @RequestParam(required = false) String transaction) {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getAllTransactions();

        StringBuilder builder = new StringBuilder();

        model.addAttribute("transactions", transactions);
        return "transactions/index";
    }


    @GetMapping("/transactions/reports")
    public String getTransactionReports(Model model, @RequestParam(required = false) String transactions)
    {
        ArrayList<String> reports;

        reports = transactionDao.getTransactionReports();

        //StringBuilder builder = new Stringbuilder();

        model.addAttribute("transactions", transactions);
        return "transactions/reports";
    }

    //Add Transaction
    @GetMapping("transactions/add")
    public String addTransaction(Model model)
    {
        model.addAttribute("transaction", new Transaction());
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

    //Error page
//    @GetMapping("transactions/404")
//    public String errorPage(Model model)
//    {
//        return "transactions/404";
//    }
//
//    @PostMapping("transactions/404")
//    public String errorPage(Model model)
//    {
//        return "redirect:/reports";
//    }
//
}