package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Entity
@Table(name = "polje")
@Data
public class Polje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_formular", nullable = false)
    @JsonIgnore // To get pure JSON response,without recursively repeating the data
    private Formular formular;

    @Column(name = "naziv")
    @NotBlank(message = "Naziv je obavezno polje!")
    @Size(max = 256)
    private String naziv;

    @Column(name = "prikazni_redosled")
    @NotNull
    private int prikazniRedosled;

    @Enumerated(EnumType.STRING)
    private PoljeEnum tip;

    @Column(name = "vreme_kreiranja")
    @CreationTimestamp // this is special hibernate annotation used for managing timestamps
    private Timestamp vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    @UpdateTimestamp
    private Timestamp vremePoslednjeIzmene;

}
