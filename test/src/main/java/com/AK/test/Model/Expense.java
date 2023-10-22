package com.AK.test.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;
    private double monthlyExpense;
    private Year expenseYear;
    @Enumerated(EnumType.STRING)
    private Month expenseMonth;


    @ManyToOne
    @JoinColumn(name = "fk_userId")
    User user;

    public Expense(Year year, Month month, Double price, User user) {
        this.expenseYear = expenseYear;
        this.expenseMonth = expenseMonth;
        this.monthlyExpense = monthlyExpense;
        this.user = user;
    }

}

