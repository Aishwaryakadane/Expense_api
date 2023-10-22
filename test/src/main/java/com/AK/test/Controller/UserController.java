package com.AK.test.Controller;

import com.AK.test.Model.Dto.PlaceOrder;
import com.AK.test.Model.Dto.SignInDto;
import com.AK.test.Model.Expense;
import com.AK.test.Model.Order;
import com.AK.test.Model.User;
import com.AK.test.Service.OrderService;
import com.AK.test.Service.TokenService;
import com.AK.test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    OrderService orderService;

    @PostMapping("userSignUp")
    public String userSignUp(@RequestBody User user){
        return userService.userSignUp(user);
    }

    @PostMapping("userSignIn")
    public String userSignIn(@RequestBody SignInDto signInDto){
        return userService.userSignIn(signInDto);
    }

    @GetMapping("expenses/monthly")
    public String getMonthlyExpenses(@RequestParam String userEmail,@RequestParam String userToken, @RequestParam Month month,@RequestParam Year year){
        if(tokenService.authenticate(userEmail,userToken)){
            return userService.checkMonthlyExpenses(month,year,userEmail);
        }
        else{
            return "you are not signIn , to check the expenses kindly signIn";
        }
    }

    @GetMapping("expense/RecentMonths/{limit}")
    public List<Expense> getMonthlyExpenseWithLimit(@RequestParam String userEmail, @RequestParam String userToken,
                                                    @PathVariable int limit)
    {
        if(tokenService.authenticate(userEmail,userToken)) {
            return userService. getMonthlyExpenseWithLimit(userEmail,limit);
        }
        throw new IllegalStateException("Not Signed In");
    }

    @PostMapping("order")
    public String placeAOrder(@RequestBody PlaceOrder order, @RequestParam String userEmail, @RequestParam String userToken){

        if(tokenService.authenticate(userEmail,userToken)) {
            return userService. placeAOrder(order,userEmail);
        }
       return "Not Signed In";
    }

}
