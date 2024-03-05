package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Entity
@Table(name = "formular")
@Getter
@Setter
public class Formular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "naziv")
    @NotBlank(message = "Naziv je obavezno polje!")
    @Size(max = 256)
    private String naziv;

    @Column(name = "vreme_kreiranja")
    @CreationTimestamp // this is special hibernate annotation used for managing timestamps
    private Timestamp vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    @UpdateTimestamp
    private Timestamp vremePoslednjeIzmene;

    @OneToMany(mappedBy = "formular", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Polje> poljeDtoList;

//    @OneToOne(mappedBy = "formular", cascade = CascadeType.ALL, orphanRemoval = true)
//    private PoljePopunjeno poljePopunjeno;

}
