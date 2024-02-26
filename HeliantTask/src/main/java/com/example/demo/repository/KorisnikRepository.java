package com.example.demo.repository;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);

    Korisnik findByRole(Role role);
}
