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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class CategoriesController {

    @Autowired
    private CategoryDao categoryDao;


    @GetMapping("/categories/index")
    public String getAllCategories(Model model, @RequestParam(required = false) String category) {
        ArrayList<Category> categories;

        categories = categoryDao.getAllCategories();

        model.addAttribute("categories", categories);

        return "categories/index";
    }

    //Add User
    @GetMapping("categories/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "add");

        return "categories/add_edit";
    }

    @PostMapping("categories/add")
    public String addCategory(Model model, @ModelAttribute("category") Category category) {
        categoryDao.addCategory(category);
        model.addAttribute("category", category);

        return "categories/add_success";
    }
}


