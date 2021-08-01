package com.iktpreobuka.dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.UcenikEntity;
import com.iktpreobuka.dnevnik.repositories.UcenikRepository;

@Service
public class LogovanjeUcenik {
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	public UcenikEntity findByUsername(String username) {
		return ucenikRepository.findByUsername(username);
	}
}
