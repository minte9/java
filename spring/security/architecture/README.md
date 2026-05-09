### Architecture

Spring Security is a `framework` that handles:

- Authentication (who you are?)
- Authorization (what can you access?)
- Protection against common attacks (CSRF, session fixation, etc)

It integrates seamlessly with the Spring ecosystem.  
It works mainly through a chain of filters that intercept HTTP requests.  

~~~java
//Simplified flow:

Client Request
    ↓
Security Filter Chain
    ↓
Authentication Filter
    ↓
Authentication Manager
    ↓
UserDetailsService (loads user)
    ↓
Security Context (stores authenticated user)
    ↓
Controller (if authorized)
~~~

### Security Filter Chain

Every request passes through multiple filters.  
It's like a pipeline, if something fails, the request is blocked.  

~~~java
// Example filters:

UsernamePasswordAuthenticationFilter
BasicAuthenticationFilter
CsrfFilter
ExceptionTranslationFilter
~~~

### Security Context

It stores current user, roles/authorities, authentication status.  
It's how spring knows who is making the request.  

~~~java
// After successful authentication:

SecurityContextHolder.getContext().getAuthentication();
~~~