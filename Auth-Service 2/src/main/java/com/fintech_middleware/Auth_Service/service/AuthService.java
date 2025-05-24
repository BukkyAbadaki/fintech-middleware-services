package com.fintech_middleware.Auth_Service.service;

import com.fintech_middleware.Auth_Service.domain.User;
import com.fintech_middleware.Auth_Service.config.JwtUtil;
import com.fintech_middleware.Auth_Service.dto.request.LoginRequest;
import com.fintech_middleware.Auth_Service.dto.request.RegisterRequest;
import com.fintech_middleware.Auth_Service.dto.response.AuthResponse;
import com.fintech_middleware.Auth_Service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthResponse register(RegisterRequest request) {

        try {
            Optional<User> userExist = userRepository.findByEmail(request.getEmail());

            if (userExist.isPresent()) {
                return new AuthResponse(null, "Email already in use", "001");
            }

            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            userRepository.save(user);

            String token = jwtUtil.generateToken(user.getEmail());
            return new AuthResponse(token, "Registration successful", "000");

        } catch (Exception ex) {
            ex.printStackTrace(); // Or use a logger
            return new AuthResponse(null, "An unexpected error occurred", "999");
        }
    }


    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token,"000","login successful" );
    }

}
