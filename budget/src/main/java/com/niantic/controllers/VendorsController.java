package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import com.niantic.services.VendorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.font.TransformAttribute;
import java.util.ArrayList;

@Controller
public class VendorsController {

    @Autowired
    private VendorDao vendorDao;

    @GetMapping("/transactions/index")
    public String getAllTransactions(Model model, @RequestParam(required = false) String transaction) {
        ArrayList<Transaction> transactions;

        transactions = transactionDao.getAllTransactions();

        StringBuilder builder = new StringBuilder();

        model.addAttribute("transactions", transactions);
        return "transactions/index";
    }

}
