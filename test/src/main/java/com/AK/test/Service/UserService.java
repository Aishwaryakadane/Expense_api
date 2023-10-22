package com.AK.test.Service;

import com.AK.test.Model.Dto.PlaceOrder;
import com.AK.test.Model.Dto.SignInDto;
import com.AK.test.Model.Expense;
import com.AK.test.Model.Order;
import com.AK.test.Model.Token;
import com.AK.test.Model.User;
import com.AK.test.Repository.IUserRepo;
import com.AK.test.Service.Email.EmailService;
import com.AK.test.Service.HashingEntity.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
   ExpenseService expenseService;

    @Autowired
    OrderService orderService;

    public String userSignUp(User user) {

        String newEmail = user.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if (existingUser != null) {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = user.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setUserPassword(encryptedPassword);


            // patient table - save patient
            userRepo.save(user);
            return "user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }


    public String userSignIn(SignInDto signInDto) {
        String email = signInDto.getEmail();

        User existingUser = userRepo.findFirstByUserEmail(email);

        if (existingUser == null) {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        String password = signInDto.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if (existingUser.getUserPassword().equals(encryptedPassword)) {
                // return a token for this sign in
                Token token = new Token(existingUser);
//
//                if (EmailService.sendEmail(email, "otp after login", token.getTokenValue())) {
//                    tokenService.createToken(token);
//                    return "check email for otp/token!!!";
//                } else {
//                    return "error while generating token!!!";
//                }


                tokenService.createToken(token);
                return token.getTokenValue();
            } else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String checkMonthlyExpenses(Month month, Year year,String userEmail) {


        User user = userRepo.findFirstByUserEmail(userEmail);

        return expenseService.checkMonthlyExpense(month,year,user);
    }

    public List<Expense> getMonthlyExpenseWithLimit(String userEmail, int limit) {
        User user = userRepo.findFirstByUserEmail(userEmail);

        return expenseService.getMonthlyExpenseWithLimit(user,limit);
    }

    public String placeAOrder(PlaceOrder order, String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);

        return orderService.placeAOrder(order,user);
    }

}
