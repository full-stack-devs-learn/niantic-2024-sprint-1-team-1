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
public class UsersController
{
    @Autowired
    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private VendorDao vendorDao;

    @GetMapping("/users/index")
    public String getAllUsers(Model model) {
        ArrayList<User> users;

        users = userDao.getAllUsers();

        model.addAttribute("users", users);

        return "users/index";
    }

    //Add User
    @GetMapping("users/add")
    public String addUser(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("action", "add");

        return "users/add_edit";
    }

    @PostMapping("users/add")
    public String addUser(Model model, @ModelAttribute("user") User user)
    {
        userDao.addUser(user);
        model.addAttribute("user", user);

        return "users/add_success";
    }

    // Edit User
    @GetMapping("users/{id}/edit")
    public String editUser(Model model, @PathVariable int id)
    {
        User user = userDao.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");

        return "users/add_edit";
    }

    @PostMapping("users/{id}/edit")
    public String editUser(@ModelAttribute("user") User user, @PathVariable int id)
    {
        user.setUserId(id);
        userDao.updateUser(user);

        return "redirect:/users/index";
    }

    // Delete User
    @GetMapping("users/{id}/delete")
    public String deleteUser(Model model, @PathVariable int id)
    {
        User user = userDao.getUserById(id);

        if(user == null)
        {
            model.addAttribute("message", String.format("User not found for id %d", id));
            return "404";
        }

        model.addAttribute("user", user);
        return "users/delete";
    }

    @PostMapping("users/{id}/delete")
    public String deleteUser(@PathVariable int id)
    {
        userDao.deleteUser(id);

        return "redirect:/users/index";
    }

}
