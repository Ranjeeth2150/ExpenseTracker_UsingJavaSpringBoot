package com.geekster.ExpenseTracker.repository;

import com.geekster.ExpenseTracker.model.AuthenticationToken;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<AuthenticationToken,Long>
{
    AuthenticationToken findFirstByTokenValue(String authTokenValue);
}
