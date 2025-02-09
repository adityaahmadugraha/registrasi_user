package com.aditya.project.service;

import com.aditya.project.User;
import com.aditya.project.UserRepository;
import com.aditya.project.dto.LoginRequest;
import com.aditya.project.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username sudah digunakan!";
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email sudah digunakan!";
        }

        User user = new User();
        user.setNama(request.getNama());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        user.setJenis_kelamin(request.getJenis_kelamin());

        userRepository.save(user);
        return "Registrasi berhasil!";
    }

    public String login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return "Login berhasil!";
        } else {
            return "Username atau password salah!";
        }
    }
}
