package com.example.demo.service.impl;

import com.example.demo.repository.KorisnikRepository;
import com.example.demo.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
                return korisnikRepository.findByKorisnickoIme(korisnickoIme)
                        .orElseThrow(() -> new UsernameNotFoundException("Korisnik not found!"));
            }
        };
    }
}
