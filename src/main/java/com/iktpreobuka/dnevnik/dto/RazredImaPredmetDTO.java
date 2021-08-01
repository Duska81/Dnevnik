package com.iktpreobuka.dnevnik.dto;

import javax.validation.constraints.NotNull;

public class RazredImaPredmetDTO {
	@NotNull(message="id predmeta must be provided.")
	private Integer idPredmet;
	@NotNull(message="id razreda must be provided.")
	private Integer idRazred;

	public RazredImaPredmetDTO() {
		super();
	}

	public Integer getIdPredmet() {
		return idPredmet;
	}

	public void setIdPredmet(Integer idPredmet) {
		this.idPredmet = idPredmet;
	}

	public Integer getIdRazred() {
		return idRazred;
	}

	public void setIdRazred(Integer idRazred) {
		this.idRazred = idRazred;
	}
}
