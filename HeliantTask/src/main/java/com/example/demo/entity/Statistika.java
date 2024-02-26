package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author Marko Kostic, on 2/26/2024
 */
@Entity
@Table(name = "statistika")
@Data
public class Statistika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "datum")
    @NotNull
    private Date datum;

    @Column(name = "broj_popunjenih_formulara")
    @NotNull
    private Integer brojPopunjenihFormulara;
}
