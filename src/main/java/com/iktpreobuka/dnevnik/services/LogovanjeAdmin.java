package com.iktpreobuka.dnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.AdminEntity;
import com.iktpreobuka.dnevnik.repositories.AdminRepository;

@Service
public class LogovanjeAdmin {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public AdminEntity findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}
}
