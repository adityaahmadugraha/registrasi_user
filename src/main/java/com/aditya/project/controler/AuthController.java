package com.aditya.project.controler;

import com.aditya.project.dto.LoginRequest;
import com.aditya.project.dto.RegisterRequest;
import com.aditya.project.model.User;
import com.aditya.project.model.ServerResponse;
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
            return ResponseEntity.ok("Register sukses");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<ServerResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            ServerResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ServerResponse errorResponse = new ServerResponse();
            errorResponse.setKode(400);
            errorResponse.setStatus(false);
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }



}
