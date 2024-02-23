package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Formular Entity
 * 
 * @author Marko Kostic
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "formular")
	private List<FormularPopunjen> popunjeniFormulari;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormular")
	private List<Polje> polja;
}
