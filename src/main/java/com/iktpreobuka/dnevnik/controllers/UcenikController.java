package com.iktpreobuka.dnevnik.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.controllers.util.RESTError;

import com.iktpreobuka.dnevnik.dto.UcenikDTO;
import com.iktpreobuka.dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;
import com.iktpreobuka.dnevnik.enumerations.EKorisnikRole;

import com.iktpreobuka.dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.dnevnik.services.LogovanjeUcenik;


@RestController
@RequestMapping(path = "/Dnevnik/ucenik")

public class UcenikController {

	@Autowired
	private UcenikRepository ucenikRepository;



	@Autowired
	private LogovanjeUcenik logovanjeUcenik;

	// dodaj ucenika
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewUcenik(@Valid @RequestBody UcenikDTO noviUcenik, BindingResult result) {

		UcenikEntity ucenik = new UcenikEntity();

		ucenik.setUsername(noviUcenik.getUsername());
		ucenik.setPassword(noviUcenik.getPassword());
		ucenik.setIme(noviUcenik.getIme());
		ucenik.setPrezime(noviUcenik.getPrezime());
		ucenik.setKorisnikRole(EKorisnikRole.ROLE_UCENIK);
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// izmeni ucenika po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateUcenik(@Valid @RequestBody UcenikDTO updateUcenik, BindingResult result,
			@PathVariable String id) {

		UcenikEntity ucenik = ucenikRepository.findById(Integer.parseInt(id)).get();

		ucenik.setUsername(updateUcenik.getUsername());
		ucenik.setPassword(updateUcenik.getPassword());
		ucenik.setIme(updateUcenik.getIme());
		ucenik.setPrezime(updateUcenik.getPrezime());
		return new ResponseEntity<UcenikEntity>(ucenikRepository.save(ucenik), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/ucenici")
	public Iterable<UcenikEntity> findAllUcenik() {

		return ucenikRepository.findAll();

	}

	// nadji ucenika po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getUcenikById(@PathVariable String id) {

		return new ResponseEntity<UcenikEntity>(ucenikRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);

	}

	// Izlistaj roditelja na osnovu ID ucenika
	@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{id}")
	public ResponseEntity<?> findRoditeljByUcenik(@PathVariable String id) {

		try {
			if (ucenikRepository.existsById(Integer.parseInt(id))) {
			}
			return new ResponseEntity<RoditeljEntity>(
					ucenikRepository.findById(Integer.parseInt(id)).get().getRoditelj(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(3, "Dogodila se greška"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<String> login(@RequestBody UcenikDTO ucenikDTO) {
		UcenikEntity ucenikEntity = logovanjeUcenik.findByUsername(ucenikDTO.getUsername());

		return new ResponseEntity<>(ucenikEntity.getUsername(), HttpStatus.OK);

	}

	
	
	  
	  }
	 
