package com.iktpreobuka.dnevnik.dto;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
public class UcenikDTO {
	
	
	@NotNull(message="Username name must be provided.")
	@Size(min=3, max=20,  message = "Username must be between {min} and {max} characters long.")
	@JsonProperty("username")
	private String username;
	
	@NotNull(message="Password name must be provided.")
	//@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,}$", message="Password is not valid.")
	@JsonProperty("password")
	private String password;
	
	@NotNull(message="First name must be provided.")
	@JsonProperty("ime")
	private String ime;
	
	@NotNull(message="Last name must be provided.")
	@JsonProperty("prezime")
	private String prezime;
	
	@NotNull(message="Repeated password name must be provided.")
	private String repeatedPassword;

	public UcenikDTO() {
		super();

	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}



	
	
}
