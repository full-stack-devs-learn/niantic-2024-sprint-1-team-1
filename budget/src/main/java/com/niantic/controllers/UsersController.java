package com.niantic.controllers;


import com.niantic.models.User;
import com.niantic.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class UsersController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users/index")
    public String getAllUsers(Model model, @RequestParam(required = false) String user) {
        ArrayList<User> users = new ArrayList<>();

        if (user == null) {
            users = userDao.getAllUsers();
        } else {
            User userFound = userDao.getUserByName(user);
            users.add(userFound);
        }

        model.addAttribute("users", users);

        return "users/index";
    }

    //Add User
    @GetMapping("users/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "add");

        return "users/add_edit";
    }

    @PostMapping("users/add")
    public String addUser(Model model, @ModelAttribute("user") User user) {
        userDao.addUser(user);
        model.addAttribute("user", user);

        return "users/add_success";
    }
}

