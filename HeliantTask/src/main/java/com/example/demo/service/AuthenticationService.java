package com.example.demo.service;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RefreshTokenRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.Korisnik;

/**
 * @author Marko Kostic, on 2/26/2024
 */
public interface AuthenticationService {

    Korisnik signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse login(LoginRequest loginRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
