## Spring Security

1. Architecture
2. Authentication Mechanisms

- In-memory authentication
- JDBC / database authentication
- Custom UserDetailsService
- Password encoding (BCrypt, etc.)

3. Authorization & Access Control

- Role-based vs authority-based access
- URL-based security (HttpSecurity)
- Method-level security (@PreAuthorize, @Secured)
- Expression-based access control

4. Security Configuration (Modern Approach)

- Replacing WebSecurityConfigurerAdapter
- Using SecurityFilterChain
- Java config vs XML (brief comparison)
- Common configuration patterns

5. JWT & Stateless Security

- What JWT is and why it’s used
- Stateless vs session-based authentication
- Implementing JWT authentication flow
- Security filters for token validation