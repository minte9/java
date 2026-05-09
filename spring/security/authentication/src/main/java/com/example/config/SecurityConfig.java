/**
 * Spring Security configuration.
 * 
 * Thie example uses:
 *  - in-memory user
 *  - BCrypt password encoder
 *  - HTTP Basic authentication 
 */
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    /**
     * Define authorization rules.
     * 
     * /public -> anyone can access
     * /private -> login required
     * /all_other_points -> login required 
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) 
                throws Exception {
        
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public").permitAll()
                .anyRequest().authenticated()            
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
