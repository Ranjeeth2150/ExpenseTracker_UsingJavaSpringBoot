package com.geekster.ExpenseTracker.controller;

import com.geekster.ExpenseTracker.model.Dto.PlaceOrder;
import com.geekster.ExpenseTracker.model.Dto.SignInInput;
import com.geekster.ExpenseTracker.model.Dto.SignUpOutput;
import com.geekster.ExpenseTracker.model.Expense;
import com.geekster.ExpenseTracker.model.Order;
import com.geekster.ExpenseTracker.model.Product;
import com.geekster.ExpenseTracker.model.User;

import com.geekster.ExpenseTracker.service.AuthenticationService;
import com.geekster.ExpenseTracker.service.ExpenseService;
import com.geekster.ExpenseTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("SignUp")
    public SignUpOutput userSignUp(@RequestBody @Valid User user)
    {
        return userService.userSignUp(user);
    }

    @PostMapping("SignIn")
    public String userLogIn(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.userLogIn(signInInput);
    }
    @PostMapping("order")
    public String placeAnOrder(@RequestBody PlaceOrder order , @RequestParam String userEmail, @RequestParam String userToken)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return userService.placeAnOrder(order,userEmail);
        }
        else
        {
            return "SignIn to Order";
        }
    }
    @GetMapping("product/id")
    public Product getProduct(@RequestParam String userEmail, @RequestParam String userToken,@RequestParam Integer productId)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return userService.getProduct(productId);
        }
        throw new IllegalStateException("Not a Valid User");
    }

    @GetMapping("Monthly/Expense")
    public String getMonthlyExpense(@RequestParam String userEmail, @RequestParam String userToken, @RequestParam  Month month ,
                                    @RequestParam Year year)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return userService.checkMonthlyExpense(month,year,userEmail);
        }
        else
        {
            return "SignIn to Check Your Monthly Expenses";
        }
    }
    @GetMapping("Yearly/Expense")
    public String getYearlyExpense(@RequestParam String userEmail, @RequestParam String userToken,@RequestParam Year year)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return userService.checkYearlyExpense(year,userEmail);
        }
        else
        {
            return "SignIn to Check Your Yearly Expenses";
        }
    }
    @GetMapping("expense/RecentMonths/{limit}")
    public List<Expense> getMonthlyExpenseWithLimit(@RequestParam String userEmail, @RequestParam String userToken,
                                                    @PathVariable int limit)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return userService. getMonthlyExpenseWithLimit(userEmail,limit);
        }
        throw new IllegalStateException("Not Signed In");
    }

    @GetMapping("products/date")
    public List<Order> getOrderedProductsWithDate(@RequestParam String userEmail, @RequestParam String userToken, @RequestParam LocalDate date)
    {
        if(authenticationService.authenticate(userEmail,userToken)) {
            return userService.getOrderedProductsWithDate(date,userEmail);
        }
        throw new IllegalStateException("Not Signed In");
    }
}