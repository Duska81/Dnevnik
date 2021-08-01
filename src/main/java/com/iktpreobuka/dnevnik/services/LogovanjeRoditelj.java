package com.iktpreobuka.dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.dnevnik.repositories.RoditeljRepository;

@Service
public class LogovanjeRoditelj {
	
	@Autowired
	private RoditeljRepository roditeljRepository;
	
	public RoditeljEntity findByUsername(String username) {
		return roditeljRepository.findByUsername(username);
	}
}

