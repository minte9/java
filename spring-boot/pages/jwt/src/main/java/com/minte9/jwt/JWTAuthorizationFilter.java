/**
 * JWT Authorization
 * 
 * JSON Web Token is an open source standard for creating access tokens 
 * that allow us to secure communications between client and server.
 * 
 * Token authentication is more secure than session authentication 
 * because a token cannot be tampered with.
 */

package com.minte9.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private final String SECRET = "myTokenSecretKey";

	/**
	 * Intercept HTTP requests and performs JWT token authentication. 
	 * 
	 * If the token is valid, it sets up Spring authentication by setting 
	 * the security context with the authenticated user's information. 
	 * 
	 * If the token is invalid, it clears the authentication context. 
	 * If an exception occurs during validation, it returns a 403
	 */
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain) 
				throws ServletException, IOException {
		try {
			if (checkJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			}else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | 
				 	UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response = (HttpServletResponse) response;
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}	

	/**
	 * Extract the JWT token from the HTTP request header and parses it 
	 * to retrieve the token's claims. 
	 * 
	 * It returns the token's claims after validating the token's signature
	 * using the application's secret key.
	 */
	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		JwtParser jwtParser = Jwts.parser();

		jwtParser.setSigningKey(SECRET.getBytes());
		return jwtParser.parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Set up Spring authentication instance with the authenticated 
	 * user's details and setting it in the security context.
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = 
			new UsernamePasswordAuthenticationToken(
				claims.getSubject(), null, authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList()
			)
		);
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	/**
	 * Checks if the Authorization header of the HTTP request contains 
	 * a JWT token with the correct Bearer prefix.
	 */
	private boolean checkJWTToken(
			HttpServletRequest request, 
			HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if ( authenticationHeader == null ||
			!authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

}