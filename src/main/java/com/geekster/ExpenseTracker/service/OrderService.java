package com.geekster.ExpenseTracker.service;

import com.geekster.ExpenseTracker.model.Dto.PlaceOrder;
import com.geekster.ExpenseTracker.model.Expense;
import com.geekster.ExpenseTracker.model.Order;
import com.geekster.ExpenseTracker.model.Product;
import com.geekster.ExpenseTracker.model.User;
import com.geekster.ExpenseTracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository iOrderRepo;
    @Autowired
    private ProductService productService;

    @Autowired
    private ExpenseService expenseService;

    public String placeAnOrder(PlaceOrder placeOrder, User user) {
        Product product = productService.getProductById(placeOrder.getProductId());
        if(product==null)
        {
            return "Not a Valid Product";
        }

        Month month = placeOrder.getOrderDate().getMonth();
        Year year = Year.of(placeOrder.getOrderDate().getYear());
        Double price = product.getProductPrice();

        Expense expense = new Expense(year,month,price,user);
        expenseService.addOrUpdateExpense(expense);

        Order order = new Order(placeOrder.getOrderDate(),product,user);
        iOrderRepo.save(order);
        return "Order Placed";
    }


    public List<Order> getOrderedProductsWithDate(LocalDate date, User user) {
        return iOrderRepo.findByUserAndOrderDate(user,date);
    }
}