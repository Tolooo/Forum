package com.example.Forum.configs;

import com.example.Forum.repositories.UserCredentialsRepository;
import com.example.Forum.services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
//                    .antMatchers("/login","/register")
//                    .permitAll()
//                    .and()
//                .authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
                .formLogin()
                    .loginPage("/login")
                    .and()
                .headers()
                    .frameOptions()
                    .sameOrigin()
                    .and()
                .csrf()
                    .disable()
                .httpBasic();

    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserCredentialsService(userCredentialsRepository));
    }

}
