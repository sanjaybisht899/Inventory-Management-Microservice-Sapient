package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Inventory Endpoints
                        .requestMatchers("/inventory/getInventoryLevels").permitAll()
                        .requestMatchers("/inventory/updateInventory").hasRole("ADMIN")
                        .requestMatchers("/inventory/coordinateWithSupplier").hasAnyRole("ADMIN", "MANAGER")

                        // Product CRUD
                        .requestMatchers("/product/add").hasRole("ADMIN")
                        .requestMatchers("/product/update").hasRole("ADMIN")
                        .requestMatchers("/product/delete/**").hasRole("ADMIN")
                        .requestMatchers("/product/all").hasAnyRole("ADMIN", "MANAGER", "USER")

                        // Supplier Endpoints
                        .requestMatchers("/supplier/coordinateWithSupplier").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/supplier/updateInventoryUponDelivery").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
