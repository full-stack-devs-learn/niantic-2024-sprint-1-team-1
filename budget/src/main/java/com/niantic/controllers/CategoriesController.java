//package com.niantic.controllers;public class CategoriesController {
//}

package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import com.niantic.services.UserDao;
import com.niantic.services.VendorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class CategoriesController {

    @Autowired
    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private VendorDao vendorDao;

    @GetMapping("/categories/index")
    public String getAllCategories(Model model, @RequestParam(required = false) String category) {
        ArrayList<Category> categories;

        categories = categoryDao.getAllCategories();

        model.addAttribute("categories", categories);

        return "categories/index";
    }


}
