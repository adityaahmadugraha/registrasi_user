package com.aditya.project.service;

import com.aditya.project.model.User;
import com.aditya.project.repository.UserRepository;
import com.aditya.project.dto.LoginRequest;
import com.aditya.project.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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
