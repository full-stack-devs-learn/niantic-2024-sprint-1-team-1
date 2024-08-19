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
public class VendorsController {

    @Autowired
    private VendorDao vendorDao;

    @GetMapping("/vendors/index")
    public String getAllUsers(Model model, @RequestParam(required = false) String vendor) {
        ArrayList<Vendor> vendors;

        vendors = vendorDao.getAllVendors();

        model.addAttribute("vendors", vendors);

        return "vendors/index";
    }

    //Add vendor
    @GetMapping("vendors/add")
    public String addVendor(Model model) {
        model.addAttribute("vendor", new Vendor());
        model.addAttribute("action", "add");

        return "vendors/add_edit";
    }

    @PostMapping("vendors/add")
    public String addVendor(Model model, @ModelAttribute("vendor") Vendor vendor) {
        vendorDao.addVendor(vendor);
        model.addAttribute("vendor", vendor);

        return "vendors/add_success";
    }
}

