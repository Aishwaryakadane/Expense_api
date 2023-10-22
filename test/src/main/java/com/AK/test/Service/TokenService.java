package com.AK.test.Service;

import com.AK.test.Model.Token;
import com.AK.test.Repository.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    ITokenRepo tokenRepo;
    public void createToken(Token token) {
            tokenRepo.save(token);
    }

    public boolean authenticate(String userEmail, String userToken) {
        Token token = tokenRepo.findFirstByTokenValue(userToken);
        if(token == null){
            return false;
        }else{

            String tokenConnectedEmail = token.getUser().getUserEmail();

            return tokenConnectedEmail.equals(userEmail);
        }
    }
}
