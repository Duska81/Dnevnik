package com.iktpreobuka.dnevnik.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.dnevnik.entities.KorisnikEntity;

public interface KorisnikService {
	ResponseEntity<?> newAdmin(KorisnikEntity newUser);
	
	

}
