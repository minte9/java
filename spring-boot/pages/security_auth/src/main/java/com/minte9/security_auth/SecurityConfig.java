/**
 * Auth Security Configuration
 * 
 * If you add Spring Security to your classpath (pom.xml), 
 * by default all endpoints will be secured.
 * 
 * You can configure Spring Security to permit access to the index page 
 * while requiring authentication for the RESTful API endpoints.
 */

package com.minte9.security_auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .and()
            .httpBasic();
    }
}