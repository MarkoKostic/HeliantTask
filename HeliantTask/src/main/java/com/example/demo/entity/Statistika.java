package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Statistika Entity
 * 
 * @author Marko Kostic
 */
@Entity
@Table(name = "Statistika")
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
