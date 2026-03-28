/**
 * App.java
 * 
 * REST controller with encrypted DB password 
 * When using a Map of lists, jdbcTemplate automatically return JSON 
 */

package com.minte9.encrypt_password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class App {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")  
    public Object users() {
        String sql = "SELECT username FROM users";
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
        return data; 
            // [{"username":"aaa"},{"username":"bbb"}]
    }
}
