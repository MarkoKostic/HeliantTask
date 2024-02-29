package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Entity
@Table(name = "formular_popunjen")
@Data
public class FormularPopunjen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_formular", nullable = false)
    @JsonIgnore // To get pure JSON response,without recursively repeating the data
    private Formular formular;

    @Column(name = "vreme_kreiranja")
    @CreationTimestamp // this is special hibernate annotation used for managing timestamps
    private Timestamp vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    @UpdateTimestamp
    private Timestamp vremePoslednjeIzmene;

}
