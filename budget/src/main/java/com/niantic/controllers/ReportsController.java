package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
@Controller
public class ReportsController {
// use this when accessing dao in another controller autowired
        @Autowired
        private TransactionDao transactionDao;

        @GetMapping("/reports")
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

                //transactions = transactionDao.getAllTransactions();
            }

            StringBuilder builder = new StringBuilder();

            model.addAttribute("transactions", transactions);
            return "transactions/reports";
        }
    }
