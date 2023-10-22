package com.geekster.ExpenseTracker.repository;

import com.geekster.ExpenseTracker.model.Expense;
import com.geekster.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer>
{
    Expense findByExpenseMonthAndExpenseYearAndUser(Month expenseMonth, Year expenseYear, User user);

    List<Expense> findByExpenseYearAndUser(Year year, User user);
    List<Expense> findByUserOrderByExpenseYearDesc(User user);

}
