package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "predmet")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PredmetEntity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	public Integer id;
	
	//@Column(nullable=false)
	public String nazivPredmeta;
	
	@Column(nullable=false)
	public Integer fond;
	@JsonIgnore
	@OneToMany(mappedBy = "predmet", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//@JsonManagedReference
	public List<PredajeEntity> predaje = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "predmet", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//@JsonManagedReference
	public List<RazredImaPredmetEntity> razredImaPredmet = new ArrayList<>();
	
	@Version
	private Integer version;
	

	public PredmetEntity() {
	
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}


	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}


	public Integer getFond() {
		return fond;
	}


	public void setFond(Integer fond) {
		this.fond = fond;
	}


	public List<PredajeEntity> getPredaje() {
		return predaje;
	}


	public void setPredaje(List<PredajeEntity> predaje) {
		this.predaje = predaje;
	}


	public List<RazredImaPredmetEntity> getRazredImaPredmet() {
		return razredImaPredmet;
	}


	public void setRazredImaPredmet(List<RazredImaPredmetEntity> razredImaPredmet) {
		this.razredImaPredmet = razredImaPredmet;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	

}
