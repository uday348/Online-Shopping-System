package com.onlineshopping.user_service.service;

import java.util.List;

import com.onlineshopping.user_service.dto.JwtResponse;
import com.onlineshopping.user_service.dto.LoginRequest;
import com.onlineshopping.user_service.dto.RegisterRequest;
import com.onlineshopping.user_service.dto.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);

    JwtResponse login(LoginRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, RegisterRequest request);

    String deleteUser(Long id);

}