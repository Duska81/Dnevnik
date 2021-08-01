package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "predajeOdeljenje")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PredajeOdeljenjeEntity {
	
	@GeneratedValue
	@Id
	private Integer id;
	
	@Version
	private Integer version;
	
	@ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "predaje")
	//@JsonBackReference
	private PredajeEntity predaje;
	
	@ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "odeljenje")
	//@JsonBackReference
	private OdeljenjeEntity odeljenje;
	@JsonIgnore
	@OneToMany(mappedBy = "predajeOdeljenje", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//@JsonBackReference
	private List<OcenaEntity> ocena = new ArrayList<>();

	public PredajeOdeljenjeEntity() {
		
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

	public PredajeEntity getPredaje() {
		return predaje;
	}

	public void setPredaje(PredajeEntity predaje) {
		this.predaje = predaje;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public List<OcenaEntity> getOcena() {
		return ocena;
	}

	public void setOcena(List<OcenaEntity> ocena) {
		this.ocena = ocena;
	}

	public void setOdeljenje(Integer idOdeljenje) {
		// TODO Auto-generated method stub
		
	}

	public void setPredaje(Integer idPredaje) {
		// TODO Auto-generated method stub
		
	}

	

	

}
