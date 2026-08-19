package com.onlineshopping.user_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineshopping.user_service.dto.JwtResponse;
import com.onlineshopping.user_service.dto.LoginRequest;
import com.onlineshopping.user_service.dto.RegisterRequest;
import com.onlineshopping.user_service.dto.UserResponse;
import com.onlineshopping.user_service.entity.Role;
import com.onlineshopping.user_service.entity.User;
import com.onlineshopping.user_service.exception.ResourceNotFoundException;
import com.onlineshopping.user_service.exception.UserAlreadyExistsException;
import com.onlineshopping.user_service.repository.UserRepository;
import com.onlineshopping.user_service.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists.");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    @Override
    public JwtResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(),
                user.getPassword())) {

            throw new ResourceNotFoundException("Invalid Email or Password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name());

        return new JwtResponse(
                user.getId(),
                token,
                user.getEmail(),
                user.getRole());
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        return mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(Long id,
                                   RegisterRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }

    @Override
    public String deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found"));

        userRepository.delete(user);

        return "User Deleted Successfully";
    }

    private UserResponse mapToUserResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }
}