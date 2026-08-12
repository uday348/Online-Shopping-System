package com.onlineshopping.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.onlineshopping.order_service.security.JwtAuthenticationFilter;
import com.onlineshopping.order_service.util.JwtUtil;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil) {
        return new JwtAuthenticationFilter(jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtFilter)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(Customizer.withDefaults())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**",
                                "/swagger-resources",
                                "/webjars/**"
                        ).permitAll()

                        // USER APIs
                        .requestMatchers(HttpMethod.POST, "/orders")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/orders/user/**")
                        .hasRole("USER")

                        // ADMIN APIs
                        .requestMatchers(HttpMethod.GET, "/orders")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/orders/*")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/orders/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/orders/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())

                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}