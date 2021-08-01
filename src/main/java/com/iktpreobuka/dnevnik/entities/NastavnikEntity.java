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
@Table(name = "nastavnik")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NastavnikEntity extends KorisnikEntity {
	
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Id
	private Integer id;

	@Column(nullable=false)
	private String email;
	@JsonIgnore
	@OneToMany//(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//@JsonManagedReference
	private List<PredajeEntity> predaje = new ArrayList<>();
	
	@Version
	private Integer version;

	
	public NastavnikEntity() {
		super();
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<PredajeEntity> getPredaje() {
		return predaje;
	}


	public void setPredaje(List<PredajeEntity> predaje) {
		this.predaje = predaje;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	
	}

}
