package com.AK.test.Repository;

import com.AK.test.Model.Expense;
import com.AK.test.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface IExpenseRepo extends JpaRepository<Expense,Long> {
    Expense findByExpenseMonthAndExpenseYearAndUser(Month month, Year year, User user);

    List<Expense> findByUserOrderByExpenseYearDesc(User user);

}
