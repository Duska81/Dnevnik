package com.iktpreobuka.dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.repositories.NastavnikRepository;

@Service
public class LogovanjeNastavnik {
	
	@Autowired
	private NastavnikRepository nastavnikRepository;
	
	public NastavnikEntity findByUsername(String username) {
		return nastavnikRepository.findByUsername(username);
	}
}
