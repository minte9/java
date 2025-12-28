/**
 * JWT Authorization Security Configuration
 * 
 * Extend WebSecurityConfigurerAdapter class to provide 
 * custom security configuration.
 * 
 * Add JWT authorization filter after UsernamePasswordAuthenticationFilter.
 * Allow unauthenticated access to /token POST requests.
 * Require authentication for all other requests.
 */

package com.minte9.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.authentication.*;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .addFilterAfter(
                new JWTAuthorizationFilter(), 
                UsernamePasswordAuthenticationFilter.class // Look Here
            )
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/token").permitAll()
            .anyRequest().authenticated();
    }
}