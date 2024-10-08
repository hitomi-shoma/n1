package com.example.nagoyameshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.service.SalesService;

@Controller
@RequestMapping("/admin/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/admin/sales")
    public String displayUserSales(@RequestParam Integer userId, Model model) {
        Integer totalSales = salesService.calculateSalesForUser(userId);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("userId", userId);
        return "admin/sales/sales-display";
    }
}



//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.example.nagoyameshi.entity.Sale;
//import com.example.nagoyameshi.entity.User;
//import com.example.nagoyameshi.service.SalesService;
//import com.example.nagoyameshi.service.UserService;
//
//@Controller
//@RequestMapping("/admin/sales")
//public class SalesController {
//    private final SalesService salesService;
//    private final UserService userService;
//
//    public SalesController(SalesService salesService, UserService userService) {
//        this.salesService = salesService;
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String displaySales(Model model) {
//        List<User> vipUsers = userService.getUsersByRole("ROLE_VIP"); // ここでStringを渡す
//        model.addAttribute("vipUsers", vipUsers);
//        return "admin/sales/sales-display";
//    }
//
//    @GetMapping("/user-sales/{userId}")
//    @ResponseBody
//    public Integer getUserSales(@PathVariable Integer userId, @RequestParam String startDate, @RequestParam String endDate) {
//        return salesService.calculateVipUserSales(userId, startDate, endDate);
//    }
//
//    @GetMapping("/period-sales")
//    @ResponseBody
//    public List<Sale> getPeriodSales(@RequestParam String startDate, @RequestParam String endDate) {
//        return salesService.getSalesByPeriod(startDate, endDate);
//    }
//}
