package com.AK.test.Service;

import com.AK.test.Model.Dto.PlaceOrder;
import com.AK.test.Model.Expense;
import com.AK.test.Model.Order;
import com.AK.test.Model.Product;
import com.AK.test.Model.User;
import com.AK.test.Repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;

@Service
public class OrderService {

    @Autowired
    IOrderRepo orderRepo;

    @Autowired
    ProductService productService;

    @Autowired
    ExpenseService expenseService;

    public String placeAOrder(PlaceOrder placeOrder, User user) {
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

        Order or = new Order(placeOrder.getOrderDate(),product,user);
        orderRepo.save(or);
        return "Order Placed";
    }

}
