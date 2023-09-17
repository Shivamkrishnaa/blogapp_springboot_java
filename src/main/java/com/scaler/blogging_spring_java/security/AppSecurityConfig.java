package com.scaler.blogging_spring_java.security;

import com.scaler.blogging_spring_java.users.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JWTService jwtService, UsersService usersService) {
        this.jwtAuthenticationFilter = new JWTAuthenticationFilter(
                new JWTAuthenticationManager(jwtService, usersService)
        );
    }

    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return jwtAuthenticationFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users","/users/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/articles","/articles/*").permitAll();
        http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
        super.configure(http);
    }

}
