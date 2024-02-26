package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_formular", nullable = false)
    private Formular formular;

    @Column(name = "vreme_kreiranja")
    @CreationTimestamp // this is special hibernate annotation used for managing timestamps
    private Timestamp vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    @UpdateTimestamp
    private Timestamp vremePoslednjeIzmene;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormularPopunjen")
    private List<PoljePopunjeno> poljaPopunjena;

}
