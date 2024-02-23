package com.example.demo.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * PoljePopunjeno Entity
 * 
 * @author Marko Kostic
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
	private FormularPopunjen idFormularPopunjen;

	@ManyToOne
	@JoinColumn(name = "id_polje", nullable = false)
	private Polje idPolje;

	@Column(name = "vrednost_tekst")
	@NotBlank(message = "Vrednost tekst je obavezno polje!")
	@Size(max = 256)
	private String vrednostTekst;

	@Column(name = "vrednost_broj")
	@NotNull
	private double vrednostBroj;

	@Column(name = "vreme_kreiranja")
	@CreationTimestamp // this is special hibernate annotation used for managing timestamps
	private Timestamp vremeKreiranja;

	@Column(name = "vreme_poslednje_izmene")
	@UpdateTimestamp
	private Timestamp vremePoslednjeIzmene;

}
