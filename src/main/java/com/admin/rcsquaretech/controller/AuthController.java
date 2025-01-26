package com.admin.rcsquaretech.controller;

import com.admin.rcsquaretech.DTO.LoginResponse;
import com.admin.rcsquaretech.DTO.ResponseVO;
import com.admin.rcsquaretech.DTO.LoginRequest;
import com.admin.rcsquaretech.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.admin.rcsquaretech.util.JwtUtil.TOKEN_VALIDITY;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseVO<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication  authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        ResponseVO<LoginResponse> responseVO = new ResponseVO<>();
        if (authenticate.isAuthenticated()) {
            String token = jwtUtil.generateToken(request.getUsername());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setExpiresIn(TOKEN_VALIDITY);
            responseVO.setStatus(200);
            responseVO.setMessage("SUCCESS");
            responseVO.setData(Collections.singletonList(loginResponse));
            return responseVO;
        }
        responseVO.setStatus(500);
        responseVO.setMessage("ERROR, Check the credentials");
        return responseVO;
    }
}
