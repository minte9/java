/**
 * REST Controller
 * 
 * In real-life applciations will have three servers: our API, 
 * authentication sever, authorization server. As demo, we can implement 
 * all three functionalities in a single application.
 * 
 * With Jwts builder() we create the JWT token with: user's name, authorities, 
 * issued and expiration dates, and signature.
 * 
 * In Spring Security, GrantedAuthority is an interface that represents 
 * a granted authority or permission (ROLE_USER, ROLE_ADMIN, etc) 
 */
package com.minte9.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AppRestController {

	@ Autowired
	private Environment env;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World! \n";
	}

	@PostMapping("/token")
	public User genToken(
			@RequestParam("user") String username, 
			@RequestParam("password") String pwd) {
		
		User user = new User();	
		user.setUser(username);
		String envUser = env.getProperty("spring.security.user.name");
		String envPass = env.getProperty("spring.security.user.password");

		if(username.equals(envUser) && pwd.equals(envPass)) { // Look Here
			String token = getJWTToken(username);
			user.setToken(token);
		}

		return user;
	}

	private String getJWTToken(String username) {
		String secretKey = "myTokenSecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
			.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
			.builder()
			.setId("minte9JWT")
			.setSubject(username)
			.claim("authorities", grantedAuthorities.stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList()))
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 600000))
			.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
			.compact();

		return "Bearer " + token;
	}
}