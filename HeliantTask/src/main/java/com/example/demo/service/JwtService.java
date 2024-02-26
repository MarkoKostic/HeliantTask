package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * @author Marko Kostic, on 2/26/2024
 */
public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

}
