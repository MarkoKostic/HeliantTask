package com.example.demo.service.impl;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RefreshTokenRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Role;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final KorisnikRepository korisnikRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public Korisnik signUp(SignUpRequest signUpRequest) {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(signUpRequest.getKorisnickoIme());
        korisnik.setEmail(signUpRequest.getEmail());
        korisnik.setRole(Role.RADNIK);
        korisnik.setLozinka(passwordEncoder.encode(signUpRequest.getLozinka()));

        return korisnikRepository.save(korisnik);
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getKorisnickoIme(), loginRequest.getLozinka()));

        var user = korisnikRepository.findByKorisnickoIme(loginRequest.getKorisnickoIme())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String korisnickoIme = jwtService.extractUserName(refreshTokenRequest.getToken());
        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), korisnik)) {
            var jwt = jwtService.generateToken(korisnik);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

}
