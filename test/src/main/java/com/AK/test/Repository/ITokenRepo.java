package com.AK.test.Repository;

import com.AK.test.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<Token,Long> {

    Token findFirstByTokenValue(String userToken);

}
