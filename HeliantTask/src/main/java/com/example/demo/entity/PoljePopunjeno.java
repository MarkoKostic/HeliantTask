package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Entity
@Table(name = "polje_popunjeno")
@Data
public class PoljePopunjeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_formular_popunjen", nullable = false)
    @JsonIgnore // To get pure JSON response,without recursively repeating the data
    private FormularPopunjen formularPopunjen;

    @OneToOne
    @JoinColumn(name = "id_polje", nullable = false)
    @JsonIgnore // To get pure JSON response,without recursively repeating the data
    private Polje polje;

    @Column(name = "vrednost_tekst")
    @Size(max = 256)
    private String vrednostTekst;

    @Column(name = "vrednost_broj")
    private double vrednostBroj;

    @Column(name = "vreme_kreiranja")
    @CreationTimestamp // this is special hibernate annotation used for managing timestamps
    private Timestamp vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    @UpdateTimestamp
    private Timestamp vremePoslednjeIzmene;

    private Integer identifier;

}
