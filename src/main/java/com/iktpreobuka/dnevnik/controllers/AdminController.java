package com.iktpreobuka.dnevnik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.controllers.util.RESTError;
import com.iktpreobuka.dnevnik.dto.AdminDTO;
import com.iktpreobuka.dnevnik.entities.AdminEntity;
import com.iktpreobuka.dnevnik.enumerations.EKorisnikRole;
import com.iktpreobuka.dnevnik.repositories.AdminRepository;

@RestController
@RequestMapping(path = "/Dnevnik/admin")

public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNoviAdmin(@Valid @RequestBody AdminDTO noviAdmin) {

		AdminEntity admin = new AdminEntity();

		admin.setUsername(noviAdmin.getUsername());
		admin.setPassword(noviAdmin.getPassword());
		admin.setIme(noviAdmin.getIme());
		admin.setPrezime(noviAdmin.getPrezime());
		admin.setEmail(noviAdmin.getEmail());
		admin.setKorisnikRole(EKorisnikRole.ROLE_ADMIN);
		adminRepository.save(admin);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllAdmin() {
		try {
			return new ResponseEntity<Iterable<AdminEntity>>(adminRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
