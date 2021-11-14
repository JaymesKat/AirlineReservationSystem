package edu.miu.ars.controller;

import edu.miu.ars.DTO.Login;
import edu.miu.ars.domain.AppUser;
import edu.miu.ars.service.AppUserService;
import edu.miu.ars.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationController(AppUserService appUserService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.appUserService = appUserService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody Login login) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword(), new ArrayList<>()));
        if (authentication != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(authentication));
        }
        return ResponseEntity.badRequest().body("Invalid Username/password");
    }
}
