package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    private final ApplicationUserDao applicationUserDao;

    public ApplicationUserService(@Qualifier("fake")ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                .SelectApplicationUserByUsername(username)
                .orElseThrow(()-> new
                        UsernameNotFoundException(String.format("Username %s not found",
                        username)));
    }
}