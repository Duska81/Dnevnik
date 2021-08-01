package com.iktpreobuka.dnevnik.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ocena")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OcenaEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private Integer vrednostOcene;
	
	@Column(name = "datum", nullable = false)
   // @CreationTimestamp
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate datum;
	
	@Column(name="polugodiste", nullable=false)
	
	private Integer polugodiste;
	
	@ManyToOne//(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenik")
	//@JsonBackReference
	private UcenikEntity ucenik;
	
	@ManyToOne//(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "predajeOdeljenje")
	//@JsonBackReference
	private PredajeOdeljenjeEntity predajeOdeljenje;
	
	@Version
	private Integer version;

	
	public OcenaEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVrednostOcene() {
		return vrednostOcene;
	}

	public void setVrednostOcene(Integer vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Integer getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(Integer polugodiste) {
		this.polugodiste = polugodiste;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public PredajeOdeljenjeEntity getPredajeOdeljenje() {
		return predajeOdeljenje;
	}

	public void setPredajeOdeljenje(PredajeOdeljenjeEntity predajeOdeljenje) {
		this.predajeOdeljenje = predajeOdeljenje;
	}

	public void setPredmet(PredmetEntity predmet) {
		// TODO Auto-generated method stub
		
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		// TODO Auto-generated method stub
		
	}

	public void setPredajeOdeljenje(Integer idPredajeOdeljenje) {
		// TODO Auto-generated method stub
		
	}

	public void setUcenik(Integer idUcenik) {
		// TODO Auto-generated method stub
		
	

	
		
	}


}
