package com.iktpreobuka.dnevnik.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.iktpreobuka.dnevnik.dto.NastavnikDTO;

import com.iktpreobuka.dnevnik.entities.NastavnikEntity;

import com.iktpreobuka.dnevnik.enumerations.EKorisnikRole;
import com.iktpreobuka.dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.dnevnik.repositories.PredajeRepository;
import com.iktpreobuka.dnevnik.services.LogovanjeNastavnik;

@RestController
@RequestMapping(path = "/Dnevnik/nastavnik")

public class NastavnikController {
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NastavnikRepository nastavnikRepository;

	@Autowired
	private PredajeRepository predajeRepository;

	@Autowired
	private LogovanjeNastavnik logovanjeNastavnik;

//dodaj novog nastavnika
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewNastavnik(@Valid @RequestBody NastavnikDTO noviNastavnik) {

		NastavnikEntity nastavnik = new NastavnikEntity();

		nastavnik.setUsername(noviNastavnik.getUsername());
		nastavnik.setPassword(noviNastavnik.getPassword());
		nastavnik.setIme(noviNastavnik.getIme());
		nastavnik.setPrezime(noviNastavnik.getPrezime());
		nastavnik.setEmail(noviNastavnik.getEmail());
		nastavnik.setKorisnikRole(EKorisnikRole.ROLE_NASTAVNIK);

		nastavnikRepository.save(nastavnik);
		log.info("novi nastavnik je unet");
		return new ResponseEntity<>(nastavnik, HttpStatus.OK);
	}

//izmeni po id
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateNastavnik(@Valid @RequestBody NastavnikDTO updateNastavnik,
			@PathVariable String id) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(Integer.parseInt(id)).get();

		nastavnik.setUsername(updateNastavnik.getUsername());
		nastavnik.setPassword(updateNastavnik.getPassword());
		nastavnik.setIme(updateNastavnik.getIme());
		nastavnik.setPrezime(updateNastavnik.getPrezime());
		nastavnik.setEmail(updateNastavnik.getEmail());

		log.info(" nastavnik je izmenjen");
		return new ResponseEntity<NastavnikEntity>(nastavnikRepository.save(nastavnik), HttpStatus.OK);
	}

//svi nastavnici
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllNastavnik() {
		try {
			return new ResponseEntity<Iterable<NastavnikEntity>>(nastavnikRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// nadji nastavnika po id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getNastavnikById(@PathVariable String id) {

		return new ResponseEntity<NastavnikEntity>(nastavnikRepository.findById(Integer.parseInt(id)).get(),
				HttpStatus.OK);

	}

//obrisi nastavnika po id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> obrisiNastavnikaById(@PathVariable String id) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(Integer.parseInt(id)).get();

		nastavnikRepository.delete(nastavnik);
		return new ResponseEntity<NastavnikEntity>(nastavnik, HttpStatus.OK);

	}

	// na osnovu id predaje izlistaj mi nastavnika
	@RequestMapping(method = RequestMethod.GET, value = "/predajeN/{id}")
	public ResponseEntity<?> findNastavnikByPredaje(@PathVariable String id) {

		return new ResponseEntity<NastavnikEntity>(
				predajeRepository.findById(Integer.parseInt(id)).get().getNastavnik(), HttpStatus.OK);

	}

//nastavnik login
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<String> login(@RequestBody NastavnikDTO nastavnikDTO) {
		NastavnikEntity nastavnikEntity = logovanjeNastavnik.findByUsername(nastavnikDTO.getUsername());
		if (nastavnikEntity == null) {
			return new ResponseEntity<>("Username doesn't exist", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(nastavnikEntity.getUsername(), HttpStatus.OK);
	}

}