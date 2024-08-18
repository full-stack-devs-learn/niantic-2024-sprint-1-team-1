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

//    private CategoryDao categoryDao = new CategoryDao();
//    private TransactionDao transactionDao = new TransactionDao();
//    private UserDao userDao = new UserDao();
//    private VendorDao vendorDao = new VendorDao();
//    @Autowired
//    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
//    @Autowired
//    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
//    @Autowired
//    private VendorDao vendorDao;

    @GetMapping("/categories/index")
    public String getAllCategories(Model model, @RequestParam(required = false) String category)
    {
        ArrayList<Category> categories;

        categories = categoryDao.getAllCategories();

        model.addAttribute("categories", categories);

        return "categories/index";
    }

    //Add User
    @GetMapping("categories/add")
    public String addCategory(Model model)
    {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "add");

        return "categories/add_edit";
    }

    @PostMapping("categories/add")
    public String addCategory(Model model, @ModelAttribute("category") Category category)
    {
        categoryDao.addCategory(category);
        model.addAttribute("category", category);

        return "categories/add_success";
    }

    // Edit Category
    @GetMapping("categories/{id}/edit")
    public String editCategory(Model model, @PathVariable int id)
    {
        Category category = categoryDao.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("action", "edit");

        return "categories/add_edit";
    }

    @PostMapping("categories/{id}/edit")
    public String editCategory(@ModelAttribute("category") Category category, @PathVariable int id)
    {
        category.setCategoryId(id);
        categoryDao.updateCategory(category);

        return "redirect:/categories/index";
    }

    // Delete category
    @GetMapping("categories/{id}/delete")
    public String deleteCategory(Model model, @PathVariable int id)
    {
        Category category = categoryDao.getCategoryById(id);

        if(category == null)
        {
            model.addAttribute("message", String.format("Category not found for id %d", id));
            return "404";
        }

        model.addAttribute("category", category);
        return "categories/delete";
    }

    @PostMapping("categories/{id}/delete")
    public String deleteCategory(@PathVariable int id)
    {
        categoryDao.deleteCategory(id);

        return "redirect:/categories/index";
    }


}
