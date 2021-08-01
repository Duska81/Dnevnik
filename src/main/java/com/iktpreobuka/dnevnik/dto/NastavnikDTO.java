package com.iktpreobuka.dnevnik.dto;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
public class NastavnikDTO {

	
	@NotNull(message="Username name must be provided.")
	@Size(min=3, max=20,  message = "Username must be between {min} and {max} characters long.")
	@JsonProperty("username")
	private String username;
	
	@NotNull(message="Password name must be provided.")
	
	@JsonProperty("password")
	private String password;
	
	@NotNull(message="ime must be provided.")
	@JsonProperty("ime")
	public String ime;
	
	@NotNull(message="prezime must be provided.")
	@JsonProperty("prezime")
	private String prezime;
	
	@NotNull(message = "Email must be provided.")
	
	@JsonProperty("email")
	private String email;
	
	@NotNull(message="Repeated password name must be provided.")
	private String repeatedPassword;


	public NastavnikDTO() {
		super();
		// TODO Auto-generated constructor stub
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRepeatedPassword() {
		return repeatedPassword;
	}


	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
}
