package com.example.demo;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Role;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@SpringBootApplication
public class HeliantTaskApplication implements CommandLineRunner {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public static void main(String[] args) {
        SpringApplication.run(HeliantTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Korisnik adminAccount = korisnikRepository.findByRole(Role.ADMIN);

        if (adminAccount == null) {
            Korisnik korisnik = new Korisnik();
            korisnik.setEmail("marko.kostic@hotmail.com");
            korisnik.setKorisnickoIme("marko");
            korisnik.setRole(Role.ADMIN);
            korisnik.setLozinka(new BCryptPasswordEncoder().encode("admin"));
            korisnikRepository.save(korisnik);
        }
    }
}
