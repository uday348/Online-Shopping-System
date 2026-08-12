package com.onlineshopping.notification_service.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.onlineshopping.notification_service.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("========== JWT FILTER START ==========");

        String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header : " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            System.out.println("Token : " + token);

            boolean valid = jwtUtil.validateToken(token);
            System.out.println("Token Valid : " + valid);

            if (valid) {

                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);

                System.out.println("Email      : " + email);
                System.out.println("Role       : " + role);
                System.out.println("Authority  : ROLE_" + role);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Authentication Set Successfully");
            } else {

                System.out.println("Invalid JWT Token");
            }

        } else {

            System.out.println("Authorization Header Missing");
        }

        System.out.println("========== JWT FILTER END ==========");

        filterChain.doFilter(request, response);
    }
}