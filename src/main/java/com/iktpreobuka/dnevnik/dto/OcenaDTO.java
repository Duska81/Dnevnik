package com.iktpreobuka.dnevnik.dto;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
public class OcenaDTO {
	
	@JsonProperty("id")
	private Integer id;
	@Min(value=1, message="Ocena mora biti minimum 1!")
	@Max(value=5, message="Ocena mora biti maksimum 5!")
	@NotNull(message = "Ocena must be provided.") 
	@JsonProperty("vrednostOcene")
	private Integer vrednostOcene;
		
	@NotNull
	@JsonProperty("datum")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate datum;
	
	@NotNull
	@JsonProperty("polugodiste")
	private Integer polugodiste;
	
	private Integer idPredajeOdeljenje;
	
	private Integer idUcenik;
	
	public OcenaDTO() {
		super();
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

	public Integer getIdPredajeOdeljenje() {
		return idPredajeOdeljenje;
	}

	public void setIdDrziNastavu(Integer idPredajeOdeljenje) {
		this.idPredajeOdeljenje = idPredajeOdeljenje;
	}

	public Integer getIdUcenik() {
		return idUcenik;
	}

	public void setIdUcenik(Integer idUcenik) {
		this.idUcenik = idUcenik;
	}

}
