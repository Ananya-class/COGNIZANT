package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // [cite: 361]
@EnableWebSecurity // [cite: 361]
public class SecurityConfig extends WebSecurityConfigurerAdapter { // [cite: 360]

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // [cite: 407]
        LOGGER.info("START: Configuring In-Memory Users");
        auth.inMemoryAuthentication() // [cite: 408]
                .withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN") // [cite: 409]
                .and()
                .withUser("user").password(passwordEncoder().encode("pwd")).roles("USER"); // [cite: 411]
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // [cite: 413, 414]
        return new BCryptPasswordEncoder(); // [cite: 416]
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception { // [cite: 419]
        httpSecurity.csrf().disable()
                .httpBasic().and() // [cite: 420]
                .authorizeRequests()
                .antMatchers("/authenticate").hasAnyRole("USER", "ADMIN") // [cite: 493]
                .anyRequest().authenticated() // [cite: 621]
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager())); // [cite: 623]
    }
}
