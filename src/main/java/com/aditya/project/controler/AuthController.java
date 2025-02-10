package com.aditya.project.controler;

import com.aditya.project.model.User;
import com.aditya.project.service.AuthService;
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
    public ResponseEntity<?> register(@RequestBody User user) {
            authService.register(user);
            return ResponseEntity.ok("User registered successfully");
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
//        return ResponseEntity.ok(authService.login(request));
//    }
}

