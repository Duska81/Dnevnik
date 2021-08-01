package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "predaje")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PredajeEntity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	@Version
	private Integer version;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nastavnik")
	//@JsonBackReference
	public NastavnikEntity nastavnik;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predmet")
	//@JsonBackReference
	public PredmetEntity predmet;
	@JsonIgnore
	@OneToMany//(mappedBy = "predaje", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//@JsonManagedReference
	public List<PredajeOdeljenjeEntity> predajeOdeljenje = new ArrayList<>();
	
	public PredajeEntity() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	public List<PredajeOdeljenjeEntity> getPredajeOdeljenje() {
		return predajeOdeljenje;
	}

	public void setPredajeOdeljenje(List<PredajeOdeljenjeEntity> predajeOdeljenje) {
		this.predajeOdeljenje = predajeOdeljenje;
	}

}