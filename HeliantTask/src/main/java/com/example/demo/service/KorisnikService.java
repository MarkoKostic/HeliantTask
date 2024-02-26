package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Marko Kostic, on 2/26/2024
 */
public interface KorisnikService {

    UserDetailsService userDetailsService();
}
