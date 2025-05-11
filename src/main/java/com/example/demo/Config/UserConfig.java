package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("admin")
                        .password("admin123")
                        .roles("ADMIN")
                        .build()
        );

        userDetailsManager.createUser(
                User.withUsername("manager")
                        .password("manager123")
                        .roles("MANAGER")
                        .build()
        );

        return userDetailsManager;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
