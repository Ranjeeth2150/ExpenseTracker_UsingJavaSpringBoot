package com.geekster.ExpenseTracker.repository;

import com.geekster.ExpenseTracker.model.Order;
import com.geekster.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer>
{
    List<Order> findByUserAndOrderDate(User user, LocalDate date);

}
