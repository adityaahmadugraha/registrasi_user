package com.aditya.project.controler;

import com.aditya.project.dto.LoginRequest;
import com.aditya.project.dto.RegisterRequest;
import com.aditya.project.model.User;
import com.aditya.project.model.LoginResponse;
import com.aditya.project.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        User user = new User();
        user.setNama(request.getNama());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setJenis_kelamin(request.getJenis_kelamin());
        user.setId_provinsi(request.getId_provinsi());
        user.setId_kota(request.getId_kota());
        user.setId_kecamatan(request.getId_kecamatan());
        user.setId_kelurahan(request.getId_kelurahan());

        try {
            authService.register(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            String response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new LoginResponse());
        }
    }

}
