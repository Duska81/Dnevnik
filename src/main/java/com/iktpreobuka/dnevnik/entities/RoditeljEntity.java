package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.dnevnik.enumerations.EKorisnikRole;

@Entity
@Table(name = "roditelj")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoditeljEntity extends KorisnikEntity {
	
	@Column(nullable=false)
	private String email;
	@JsonIgnore
	@OneToMany//(mappedBy = "roditelj", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	//@JsonBackReference
	private List<UcenikEntity> ucenik = new ArrayList<>();
	
	@Version
	private Integer version;

	public RoditeljEntity() {
		super();
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UcenikEntity> getUcenik() {
		return ucenik;
	}

	public void setUcenik(List<UcenikEntity> ucenik) {
		this.ucenik = ucenik;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	

}
