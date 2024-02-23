package com.example.demo.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Korisnik Entity
 * 
 * @author Marko Kostic
 */
@Entity
@Table(name = "korisnik")
@Data
public class Korisnik {

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

	@Column(name = "vreme_kreiranja")
	@CreationTimestamp // this is special hibernate annotation used for managing timestamps
	private Timestamp vremeKreiranja;

	@Column(name = "vreme_poslednje_izmene")
	@UpdateTimestamp
	private Timestamp vremePoslednjeIzmene;

}
