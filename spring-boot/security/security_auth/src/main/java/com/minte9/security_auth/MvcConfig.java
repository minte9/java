/**
 * App Configuration
 * 
 * WebMvcConfigurer is used to configure view controllers 
 * that return HTML pages.
 * 
 * AddViewControllers() method is used to register view controllers 
 * that map specific URLs to view names.
 * 
 * In general, using @GetMapping annotations in your controller 
 * is the more common and flexible approach, but defining view controllers 
 * can be a useful tool in certain situations. 
 */

package com.minte9.security_auth;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/user").setViewName("user");
    }
}