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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * FormularPopunjen Entity
 * 
 * @author Marko Kostic
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
