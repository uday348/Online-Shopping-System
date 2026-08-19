package com.onlineshopping.user_service.dto;

import com.onlineshopping.user_service.entity.Role;

public class JwtResponse {

    private Long userId;
    private String token;
    private String email;
    private Role role;

    public JwtResponse() {
    }

    public JwtResponse(Long userId,
                       String token,
                       String email,
                       Role role) {

        this.userId = userId;
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}