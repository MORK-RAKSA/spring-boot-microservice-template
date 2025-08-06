package com.raksa.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
    /**
     * Password encoder bean for encoding passwords.
     * This uses BCrypt hashing algorithm to securely hash passwords.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
