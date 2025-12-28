/** 
 * Mysql App to get data (json)
 * 
 * Query a database in Spring Boot without using JPA 
 * or creating a model class.
 * 
 * The jdbcTemplate.queryForList() method is designed to 
 * return a list of rows from the database
 * 
 * By returning the list of maps from the controller method, 
 * Spring will automatically convert it to a JSON object
 * 
 * The Jackson library to get json is included as a transitive dependency 
 * of the spring-boot-starter-web dependency.
 */

package com.minte9.jdbc_mysql;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class App {

    // inject an instance of JdbcTemplate into the class
    @Autowired 
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")  
    public String users() {
        String sql = "SELECT username FROM users";
        List<String> users = jdbcTemplate.queryForList(sql, String.class);
        return "Users: " + users.toString();
    }

    @GetMapping(path = "/getusers", produces = "application/json")
    public List<Map<String, Object>> users_json() {
        String sql = "SELECT email, username FROM users";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        return rows; // json
    }
}