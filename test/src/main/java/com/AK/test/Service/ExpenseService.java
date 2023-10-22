package com.AK.test.Service;

import com.AK.test.Model.Expense;
import com.AK.test.Model.User;
import com.AK.test.Repository.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    IExpenseRepo expenseRepo;

    public String checkMonthlyExpense(Month month, Year year, User user) {

        Expense expense = expenseRepo.findByExpenseMonthAndExpenseYearAndUser(month,year,user);
        if(expense != null)
        {
            return "Your Expenses for "+year+"/"+month.toString()+" is "+expense.getMonthlyExpense();
        }
        return "You don't have Expense for the Month of "+month.toString();
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
}
