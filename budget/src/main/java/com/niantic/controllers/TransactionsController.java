package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class TransactionsController
{
    private TransactionDao transactionDao = new TransactionDao();

    @GetMapping("/transactions")
    public String getAllTransactions(Model model, @RequestParam(required = false) String transaction)
    {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getAllTransactions();

        StringBuilder builder = new StringBuilder();

        model.addAttribute("transactions", transactions);
        return "transactions/index";
    }
}
