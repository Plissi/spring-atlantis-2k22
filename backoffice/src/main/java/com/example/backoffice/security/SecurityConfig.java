package com.example.backoffice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();

        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");

        //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal, password as credentials from users where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.formLogin();
        http.authorizeRequests().antMatchers("/index/**", "/users/**", "/devices/**", "/associate/**").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
