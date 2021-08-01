package com.iktpreobuka.dnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.dnevnik.enumerations.EKorisnikRole;

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminEntity extends KorisnikEntity {

	@Column(nullable=false)
	private String email;
	
	@Version
	private Integer version;

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	

}
