package com.example.demo.security;

import com.example.demo.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.demo.security.AppUserPermission.COURSE_WRITE;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
    //  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(HttpMethod.DELETE,"/maganement/api/**").hasAuthority(COURSE_WRITE.name())
                .requestMatchers(HttpMethod.POST,"/maganement/api/**").hasAuthority(COURSE_WRITE.name())
                .requestMatchers(HttpMethod.PUT,"/maganement/api/**").hasAuthority(COURSE_WRITE.name())
                .requestMatchers(HttpMethod.GET,"/maganement/api/**").hasAnyRole(AppUserRole.ADMIN.name())
                .requestMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    private final  PasswordEncoder passwordEncoder;
    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) { this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    private final ApplicationUserService applicationUserService;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){ DaoAuthenticationProvider provider=new
            DaoAuthenticationProvider(); provider.setPasswordEncoder(passwordEncoder);
            provider.setUserDetailsService(applicationUserService); return provider;
    }


}