package com.geekster.ExpenseTracker.service;

import com.geekster.ExpenseTracker.model.Expense;
import com.geekster.ExpenseTracker.model.User;
import com.geekster.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;

    public void addOrUpdateExpense(Expense expense) {
        Expense existingExpense = expenseRepo.findByExpenseMonthAndExpenseYearAndUser(expense.getExpenseMonth(),
                expense.getExpenseYear(),expense.getUser());

        if(existingExpense!=null)
        {
            existingExpense.setMonthlyExpense(existingExpense.getMonthlyExpense()+expense.getMonthlyExpense());
            expenseRepo.save(existingExpense);
        }
        else {
            expenseRepo.save(expense);
        }

    }

    public String checkMonthlyExpense(Month month, Year year, User user) {

        Expense expense = expenseRepo.findByExpenseMonthAndExpenseYearAndUser(month,year,user);
        if(expense != null)
        {
            return "Your Expenses for "+year+"/"+month.toString()+" is "+expense.getMonthlyExpense();
        }
        return "You don't have Expense for the Month of "+month.toString();
    }


    public String checkYearlyExpense(Year year, User user) {
        List<Expense> expensesList = expenseRepo.findByExpenseYearAndUser(year,user);

        Double totalYearExpense = 0.0;
        for(Expense i:expensesList)
        {
            totalYearExpense+= i.getMonthlyExpense();
        }

        return "Your expenses for the year of "+year+" is: "+totalYearExpense;

    }

    public List<Expense> getMonthlyExpenseWithLimit(User user, int limit) {
        List<Expense> expenseList = expenseRepo.findByUserOrderByExpenseYearDesc(user);
        if(limit>expenseList.size())
        {
            return expenseList;
        }
        List<Expense>expenses = new ArrayList<>();


        for(int i = 0;i<limit;i++)
        {
            expenses.add(expenseList.get(i));
        }

        return expenses;
    }
}