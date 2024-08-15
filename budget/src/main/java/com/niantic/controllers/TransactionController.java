package com.niantic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController
{ private TrasactionDao transactionDao = new TransactionDao();

    @GetMapping("/")
    public String transaction(Model model)
    {
        model.addAttribute("pageTitle", "Budget Pal :: Transaction");
        return "index";
    }
}
