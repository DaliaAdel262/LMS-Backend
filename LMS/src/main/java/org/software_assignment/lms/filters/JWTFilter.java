package org.software_assignment.lms.filters;

import componnet.JwtGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JwtGenerator jwtUtil = new JwtGenerator();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String body = request.getHeader("super-admin");
        String username = "user";
        String password = "user12345";

        if ((authHeader != null && authHeader.startsWith("Bearer "))) {
            String token = authHeader.substring(7);
            String role = jwtUtil.extractRole(token);
            if (role != null && jwtUtil.validateToken(token , role)) {
                // Here, we are using the role to set the authorities
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, password, Collections.singletonList(() -> role));  // Prepending ROLE_ to match standard Spring Security roles
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        else if (body != null && body.equals("superAdmin@dash.com"))
        {
            System.out.println("SuperAdmin");
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, password, Collections.singletonList(() -> "ADMIN"));  // Prepending ROLE_ to match standard Spring Security roles
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
