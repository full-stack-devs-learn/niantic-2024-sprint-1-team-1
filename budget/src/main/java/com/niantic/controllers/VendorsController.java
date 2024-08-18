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
public class VendorsController
{
    private CategoryDao categoryDao = new CategoryDao();
    private TransactionDao transactionDao = new TransactionDao();
    private UserDao userDao = new UserDao();
    private VendorDao vendorDao = new VendorDao();

//    @Autowired
//    private TransactionDao transactionDao; //transactionDao = new TransactionDao(dataSource);
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private CategoryDao categoryDao;
//    @Autowired
//    private VendorDao vendorDao;

    @GetMapping("/vendors/index")
    public String getAllUsers(Model model, @RequestParam(required = false) String vendor) {
        ArrayList<Vendor> vendors;

        vendors = vendorDao.getAllVendors();

        model.addAttribute("vendors", vendors);

        return "vendors/index";
    }

    //Add vendor
    @GetMapping("vendors/add")
    public String addVendor(Model model)
    {
        model.addAttribute("vendor", new Vendor());
        model.addAttribute("action", "add");

        return "vendors/add_edit";
    }

    @PostMapping("vendors/add")
    public String addVendor(Model model, @ModelAttribute("vendor") Vendor vendor)
    {
        vendorDao.addVendor(vendor);
        model.addAttribute("vendor", vendor);

        return "vendors/add_success";
    }

    // Edit vendor
    @GetMapping("vendors/{id}/edit")
    public String editVendor(Model model, @PathVariable int id)
    {
        Vendor vendor = vendorDao.getVendorById(id);
        model.addAttribute("vendor", vendor);
        model.addAttribute("action", "edit");

        return "vendors/add_edit";
    }

    @PostMapping("vendorss/{id}/edit")
    public String editVendor(@ModelAttribute("vendor") Vendor vendor, @PathVariable int id)
    {
        vendor.setVendorId(id);
        vendorDao.updateVendor(vendor);

        return "redirect:/vendors/index";
    }

    // Delete Vendor
    @GetMapping("vendors/{id}/delete")
    public String deleteVendor(Model model, @PathVariable int id)
    {
        Vendor vendor = vendorDao.getVendorById(id);

        if(vendor == null)
        {
            model.addAttribute("message", String.format("vendor not found for id %d", id));
            return "404";
        }

        model.addAttribute("vendor", vendor);
        return "vendors/delete";
    }

    @PostMapping("vendors/{id}/delete")
    public String deleteVendor(@PathVariable int id)
    {
        vendorDao.deleteVendor(id);

        return "redirect:/vendors/index";
    }

}
