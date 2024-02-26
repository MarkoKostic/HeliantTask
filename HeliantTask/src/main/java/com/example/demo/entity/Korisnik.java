package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Data
@Entity
@Table(name = "korisnik")
public class Korisnik implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "korisnicko_ime")
    @NotBlank(message = "Korisnicko ime je obavezno polje!")
    @Size(max = 256)
    private String korisnickoIme;

    @Column(name = "lozinka")
    @NotBlank(message = "Lozinka je obavezno polje!")
    @Size(max = 256)
    private String lozinka;

    @Column(name = "email")
    @NotBlank(message = "Email je obavezno polje!")
    @Size(max = 256)
    private String email;

    private Role role;

    @Column(name = "vreme_kreiranja")
    @CreationTimestamp // this is special hibernate annotation used for managing timestamps
    private Timestamp vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    @UpdateTimestamp
    private Timestamp vremePoslednjeIzmene;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return lozinka;
    }


    @Override
    public String getUsername() {
        return korisnickoIme;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
