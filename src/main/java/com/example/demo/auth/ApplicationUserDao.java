package com.example.demo.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Optional;
@ComponentScan
@Component
public interface ApplicationUserDao {
    public Optional<ApplicationUser> SelectApplicationUserByUsername (String username);
}