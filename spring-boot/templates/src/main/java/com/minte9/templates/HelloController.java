/**
 * Spring Boot MVC Controller
 * The Controller handle GET requests and returns the name of 
 * the View responsible to render html content.
 * 
 * Keep in mind that templates will not work in case that 
 * you have a @RestController
 */

package com.minte9.templates;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/message")
    @ResponseBody
    public String message() {
        return "My message"; // just String
    }
    
    /**
     * RequestParam binds the value of GET username
     * into the name parameter of hello() method
     */
    @GetMapping("/hello")
    public String hello(
        @RequestParam(name="username", required=false, defaultValue="guest") 
        String str, Model model) 
    {
        
        model.addAttribute("name", str);
        return "hello"; // .html can be omitted
    }
}