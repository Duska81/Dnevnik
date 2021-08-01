package com.iktpreobuka.dnevnik.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
public class PredmetDTO {

	@NotNull(message="Naziv predmeta must be provided.")
	//@JsonProperty("naziv_predmeta")
	public String nazivPredmeta;
	
	@NotNull(message="Fond must be provided.")
	//@JsonProperty("fond")
	public Integer fond;

	public PredmetDTO() {
		super();
		// TODO Auto-generated constructor stub
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
}
