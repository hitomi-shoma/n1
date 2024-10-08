package com.example.nagoyameshi.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Sale;
import com.example.nagoyameshi.repository.SalesRepository;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Integer calculateSalesForUser(Integer userId) {
        List<Sale> sales = salesRepository.findByUserId(userId);
        int totalSales = 0;

        for (Sale sale : sales) {
            long monthsBetween = ChronoUnit.MONTHS.between(sale.getStartDate(), sale.getEndDate()) + 1; // 月数を計算
            totalSales += monthsBetween * sale.getAmount(); // 売上を計算
        }

        return totalSales;
    }

    public List<Sale> getSalesByUserId(Integer userId) {
        return salesRepository.findByUserId(userId);
    }
}



//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.example.nagoyameshi.entity.Role;
//import com.example.nagoyameshi.entity.Sale;
//import com.example.nagoyameshi.entity.User;
//import com.example.nagoyameshi.repository.RoleRepository;
//import com.example.nagoyameshi.repository.SalesRepository;
//import com.example.nagoyameshi.repository.UserRepository;  
//
//@Service
//public class SalesService {
//    private final SalesRepository salesRepository;
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//    public SalesService(SalesRepository salesRepository, UserRepository userRepository,RoleRepository roleRepository) {
//        this.salesRepository = salesRepository;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    public Integer calculateUserSales(Integer userId) {
//        return salesRepository.sumAmountByUserId(userId);
//    }
//    
//    public Integer calculateVipUserSales(Integer userId, String startDate, String endDate) {
//        // 月数を計算するロジックを追加
//        LocalDate start = LocalDate.parse(startDate);
//        LocalDate end = LocalDate.parse(endDate);
//        long monthsBetween = ChronoUnit.MONTHS.between(start, end) + 1; // 開始月も含める
//
//        return 300 * (int) monthsBetween; // 売上合計を計算
//    }
//
//
//    public List<Sale> getSalesByPeriod(String startDate, String endDate) {
//        return salesRepository.findByDateRange(startDate, endDate);
//    }
//    
//    public List<User> getUsersByRole(String roleName) {
//        Role role = roleRepository.findByName(roleName);
//        return userRepository.findByRole(role);
//    }
//}